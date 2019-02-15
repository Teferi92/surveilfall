/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.santirivera.surveilfall.domain.usecases.base

import java.lang.ref.WeakReference
import java.util.concurrent.Future

import es.santirivera.surveilfall.data.exceptions.NetworkUnavailableException
import es.santirivera.surveilfall.data.exceptions.RepositoryException
import es.santirivera.surveilfall.data.exceptions.WSNetworkException
import es.santirivera.surveilfall.data.exceptions.WSUnexpectedCodeException
import es.santirivera.surveilfall.data.exceptions.WSUnexpectedResponseException


/**
 * Runs [UseCase]s using a [UseCaseScheduler].
 */
class UseCaseHandler(private val mUseCaseScheduler: UseCaseScheduler) {

    fun <Result, Err : StringErrorOutput> execute(useCase: UseCase<Void, Result, Err>, callback: UseCaseTaggedCallback<Result, Err>): Future<*> {
        return internalExecute("", useCase, callback)
    }

    fun <Result, Err : StringErrorOutput> execute(
            tag: String, useCase: UseCase<Void, Result, Err>, callback: UseCaseTaggedCallback<Result, Err>): Future<*> {
        return internalExecute(tag, useCase, callback)
    }

    fun <Request, Result, Err : StringErrorOutput> execute(useCase: UseCase<Request, Result, Err>, values: Request, callback: UseCaseTaggedCallback<Result, Err>): Future<*> {
        useCase.setRequestValues(values)
        return internalExecute(useCase.tag, useCase, callback)
    }

    fun <Request, Result, Err : StringErrorOutput> execute(
            tag: String, useCase: UseCase<Request, Result, Err>, values: Request, callback: UseCaseTaggedCallback<Result, Err>): Future<*> {
        useCase.setRequestValues(values)
        return internalExecute(tag, useCase, callback)
    }

    private fun <Request, Result, Err : StringErrorOutput> internalExecute(
            tag: String, useCase: UseCase<Request, Result, Err>, callback: UseCaseTaggedCallback<Result, Err>): Future<*> {

        val weakCallback = WeakReference(callback)

        // The network request might be handled in a different thread so make sure Espresso knows that the app is busy until the response is handled.

        return mUseCaseScheduler.execute {
            val useCaseResponse: UseCaseResponse<Result, Err>
            try {
                useCaseResponse = useCase.run()
                if (useCaseResponse.isOkResult) {
                    notifyResponse(tag, useCaseResponse.getOkResult(), weakCallback)
                } else {
                    notifyError(tag, useCaseResponse.getErrorResult(), weakCallback)
                }
            } catch (e: NetworkUnavailableException) {
                e.printStackTrace()
                notifyNetworkUnavailable(tag, weakCallback)
            } catch (e: WSUnexpectedCodeException) {
                e.printStackTrace()
                notifyGenericError(tag, weakCallback)
            } catch (e: WSUnexpectedResponseException) {
                e.printStackTrace()
                notifyGenericError(tag, weakCallback)
            } catch (e: WSNetworkException) {
                e.printStackTrace()
                notifyGenericError(tag, weakCallback)
            } catch (e: RepositoryException) {
                e.printStackTrace()
                notifyGenericError(tag, weakCallback)
            } catch (e: RuntimeException) {
                e.printStackTrace()
                notifyGenericError(tag, weakCallback)
            }
        }
    }

    private fun <Result, Err : StringErrorOutput> notifyResponse(
            tag: String, response: Result, weakCallback: WeakReference<UseCaseTaggedCallback<Result, Err>>) {
        val callback = weakCallback.get()
        if (callback != null ) {
            mUseCaseScheduler.onResponseSuccess(tag, response, callback)
        }
    }

    private fun <Result, Err : StringErrorOutput> notifyError(
            tag: String, error: Err, weakCallback: WeakReference<UseCaseTaggedCallback<Result, Err>>) {
        val callback = weakCallback.get()
        if (callback != null) {
            mUseCaseScheduler.onError(tag, error, callback)
        }
    }


    private fun <Result, Err : StringErrorOutput> notifyNetworkUnavailable(tag: String, weakCallback: WeakReference<UseCaseTaggedCallback<Result, Err>>) {
        val callback = weakCallback.get()
        if (callback != null) {
            mUseCaseScheduler.onNetworkUnavailable(tag, callback)
        }
    }

    private fun <Result, Err : StringErrorOutput> notifyGenericError(
            tag: String, weakCallback: WeakReference<UseCaseTaggedCallback<Result, Err>>) {
        val callback = weakCallback.get()
        if (callback != null) {
            mUseCaseScheduler.onGenericError(tag, callback)
        }
    }
}

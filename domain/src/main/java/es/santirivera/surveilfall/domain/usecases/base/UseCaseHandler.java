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

package es.santirivera.surveilfall.domain.usecases.base;

import java.lang.ref.WeakReference;
import java.util.concurrent.Future;

import es.santirivera.surveilfall.data.exceptions.NetworkUnavailableException;
import es.santirivera.surveilfall.data.exceptions.RepositoryException;
import es.santirivera.surveilfall.data.exceptions.WSNetworkException;
import es.santirivera.surveilfall.data.exceptions.WSUnexpectedCodeException;
import es.santirivera.surveilfall.data.exceptions.WSUnexpectedResponseException;


/**
 * Runs {@link UseCase}s using a {@link UseCaseScheduler}.
 */
public class UseCaseHandler {

	private final UseCaseScheduler mUseCaseScheduler;

	public UseCaseHandler(UseCaseScheduler useCaseScheduler) {
		mUseCaseScheduler = useCaseScheduler;
	}

	public <Result, Err extends StringErrorOutput> Future execute(final UseCase<Void, Result, Err> useCase, UseCaseTaggedCallback<Result, Err> callback) {
		return internalExecute(null, useCase, callback);
	}

	public <Result, Err extends StringErrorOutput> Future execute(
			final String tag, final UseCase<Void, Result, Err> useCase, UseCaseTaggedCallback<Result, Err> callback) {
		return internalExecute(tag, useCase, callback);
	}

	public <Request, Result, Err extends StringErrorOutput> Future execute(final UseCase<Request, Result, Err> useCase, Request values, UseCaseTaggedCallback<Result, Err> callback) {
		useCase.setRequestValues(values);
		return internalExecute(useCase.getTag(), useCase, callback);
	}

	public <Request, Result, Err extends StringErrorOutput> Future execute(
			final String tag, final UseCase<Request, Result, Err> useCase, Request values, UseCaseTaggedCallback<Result, Err> callback) {
		useCase.setRequestValues(values);
		return internalExecute(tag, useCase, callback);
	}

	private <Request, Result, Err extends StringErrorOutput> Future internalExecute(
			final String tag, final UseCase<Request, Result, Err> useCase, UseCaseTaggedCallback<Result, Err> callback) {

		final WeakReference<UseCaseTaggedCallback<Result, Err>> weakCallback = new WeakReference<>(callback);

		// The network request might be handled in a different thread so make sure Espresso knows that the app is busy until the response is handled.

		return mUseCaseScheduler.execute(new Runnable() {
			@Override
			public void run() {

				UseCaseResponse<Result, Err> useCaseResponse;
				try {
					useCaseResponse = useCase.run();
					if (useCaseResponse.isOkResult()) {
						notifyResponse(tag, useCaseResponse.getOkResult(), weakCallback);
					} else {
						notifyError(tag, useCaseResponse.getErrorResult(), weakCallback);
					}
				} catch (NetworkUnavailableException e) {
					notifyNetworkUnavailable(tag, weakCallback);
				} catch (WSUnexpectedCodeException | WSUnexpectedResponseException | WSNetworkException | RepositoryException e) {
					notifyGenericError(tag, weakCallback);
				} catch (RuntimeException e) {
					notifyGenericError(tag, weakCallback);
				}

			}
		});
	}

	private <Result, Err extends StringErrorOutput> void notifyResponse(
			final String tag, final Result response, final WeakReference<UseCaseTaggedCallback<Result, Err>> weakCallback) {
		UseCaseTaggedCallback<Result, Err> callback = weakCallback.get();
		if (callback != null && callback.isReady()) {
			mUseCaseScheduler.onResponseSuccess(tag, response, callback);
		}
	}

	private <Result, Err extends StringErrorOutput> void notifyError(
			final String tag, final Err error, final WeakReference<UseCaseTaggedCallback<Result, Err>> weakCallback) {
		UseCaseTaggedCallback<Result, Err> callback = weakCallback.get();
		if (callback != null && callback.isReady()) {
			mUseCaseScheduler.onError(tag, error, callback);
		}
	}


	private <Result, Err extends StringErrorOutput> void notifyNetworkUnavailable(String tag, WeakReference<UseCaseTaggedCallback<Result, Err>> weakCallback) {
		UseCaseTaggedCallback<Result, Err> callback = weakCallback.get();
		if (callback != null && callback.isReady()) {
			mUseCaseScheduler.onNetworkUnavailable(tag, callback);
		}
	}

	private <Result, Err extends StringErrorOutput> void notifyGenericError(
			final String tag, final WeakReference<UseCaseTaggedCallback<Result, Err>> weakCallback) {
		UseCaseTaggedCallback<Result, Err> callback = weakCallback.get();
		if (callback != null && callback.isReady()) {
			mUseCaseScheduler.onGenericError(tag, callback);
		}
	}
}

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

import java.util.concurrent.Future;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */
public interface UseCaseScheduler {

	Future execute(Runnable runnable);

	<Result> void onResponseSuccess(final String tag, final Result response, final UseCaseTaggedCallback<Result, ?> useCaseCallback);

	<Err extends StringErrorOutput> void onError(final String tag, final Err error, final UseCaseTaggedCallback<?, Err> useCaseCallback);


	void onGenericError(final String tag, final UseCaseTaggedCallback<?, ?> useCaseCallback);

	void onNetworkUnavailable(final String tag, final UseCaseTaggedCallback<?, ?> useCaseCallback);
}
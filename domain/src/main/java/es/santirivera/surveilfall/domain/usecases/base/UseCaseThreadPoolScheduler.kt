package es.santirivera.surveilfall.domain.usecases.base

import android.os.Handler
import android.os.Looper

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

import javax.inject.Inject

/**
 * Executes asynchronous tasks using a [ThreadPoolExecutor].
 *
 *
 * See also [Executors] for a list of factory methods to create common
 * [java.util.concurrent.ExecutorService]s for different scenarios.
 */
class UseCaseThreadPoolScheduler(private val mHandler: Handler) : UseCaseScheduler {
    private val mThreadPoolExecutor: ThreadPoolExecutor

    @Inject
    constructor() : this(Handler(Looper.getMainLooper()))

    init {
        mThreadPoolExecutor = ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT.toLong(), TimeUnit.SECONDS, ArrayBlockingQueue(MAX_QUEUE_SIZE))
    }

    override fun execute(runnable: Runnable): Future<*> {
        return mThreadPoolExecutor.submit(runnable)
    }

    override fun <Result> onResponseSuccess(tag: String, response: Result, useCaseCallback: UseCaseTaggedCallback<Result, *>) {
        mHandler.post { useCaseCallback.onSuccess(tag, response) }
    }

    override fun <Err : StringErrorOutput> onError(tag: String, error: Err, useCaseCallback: UseCaseTaggedCallback<*, Err>) {
        mHandler.post { useCaseCallback.onError(tag, error) }
    }

    override fun onGenericError(tag: String, useCaseCallback: UseCaseTaggedCallback<*, *>) {
        mHandler.post { useCaseCallback.onGenericError(tag) }
    }

    override fun onNetworkUnavailable(tag: String, useCaseCallback: UseCaseTaggedCallback<*, *>) {
        mHandler.post { useCaseCallback.onNetworkUnavailable(tag) }
    }

    companion object {
        private const val POOL_SIZE = 2
        private const val MAX_POOL_SIZE = 10
        private const val MAX_QUEUE_SIZE = 150
        private const val TIMEOUT = 600
    }
}

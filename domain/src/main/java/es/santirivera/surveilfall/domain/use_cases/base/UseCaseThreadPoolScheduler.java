package es.santirivera.surveilfall.domain.use_cases.base;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

	private static final int POOL_SIZE = 2;
	private static final int MAX_POOL_SIZE = 10;
	private static final int MAX_QUEUE_SIZE = 150;
	private static final int TIMEOUT = 600;
	private final Handler mHandler;
	private ThreadPoolExecutor mThreadPoolExecutor;

	@Inject
	public UseCaseThreadPoolScheduler() {
		this(new Handler(Looper.getMainLooper()));
	}

	public UseCaseThreadPoolScheduler(Handler handler) {
		mHandler = handler;
		mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE));
	}

	@Override
	public Future execute(Runnable runnable) {
		return mThreadPoolExecutor.submit(runnable);
	}

	@Override
	public <Result> void onResponseSuccess(final String tag, final Result response, final UseCaseTaggedCallback<Result, ?> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onSuccess(tag, response);
			}
		});
	}

	@Override
	public <Err extends StringErrorOutput> void onError(final String tag, final Err error, final UseCaseTaggedCallback<?, Err> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onError(tag, error);
			}
		});
	}

	@Override
	public void onGenericError(final String tag, final UseCaseTaggedCallback<?, ?> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onGenericError(tag);
			}
		});
	}

	@Override
	public void onNetworkUnavailable(final String tag, final UseCaseTaggedCallback<?, ?> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onNetworkUnavailable(tag);
			}
		});
	}
}

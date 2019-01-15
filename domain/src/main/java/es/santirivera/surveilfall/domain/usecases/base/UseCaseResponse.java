package es.santirivera.surveilfall.domain.usecases.base;

import androidx.annotation.NonNull;


public class UseCaseResponse<Result, Err> {

	public static <Result, Err> UseCaseResponse<Result, Err> ok(@NonNull Result okResult) {
		return new UseCaseResponse<>(okResult, null);
	}

	public static <Result, Err> UseCaseResponse<Result, Err> ok() {
		return new UseCaseResponse<>(null, null);
	}

	public static <Result, Err> UseCaseResponse<Result, Err> error(@NonNull Err errorResult) {
		return new UseCaseResponse<>(null, errorResult);
	}

	protected final Result okResult;
	protected final Err errorResult;

	boolean isOkResult() {
		return errorResult == null;
	}

	Result getOkResult() {
		if (!isOkResult()) {
			throw new IllegalStateException("cannot call to getOkResult if !isOkResult!");
		}
		return okResult;
	}

	@NonNull
	Err getErrorResult() {
		if (isOkResult()) {
			throw new IllegalStateException("cannot call to getErrorResult if isOkResult!");
		}
		if (errorResult instanceof Void) {
			throw new RuntimeException("Void has no error!");
		}
		return errorResult;
	}

	// Prevent instances
	protected UseCaseResponse(Result okResult, Err errorResult) {
		this.okResult = okResult;
		this.errorResult = errorResult;
	}
}

package es.santirivera.surveilfall.domain.use_cases.base;

import androidx.annotation.NonNull;

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Request> UseCase input Params
 * @param <Result>  UseCase Result OK output
 * @param <Err>     UseCase Result ERROR output
 */
public abstract class UseCase<Request, Result, Err extends StringErrorOutput> {

    private Request mRequestValues;

    public String getTag() {
        return getClass().getSimpleName();
    }

    public void setRequestValues(Request requestValues) {
        mRequestValues = requestValues;
    }

    UseCaseResponse<Result, Err> run() {
        return executeUseCase(mRequestValues);
    }

    @NonNull
    protected abstract UseCaseResponse<Result, Err> executeUseCase(Request requestValues);
}

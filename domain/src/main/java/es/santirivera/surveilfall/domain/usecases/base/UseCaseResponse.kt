package es.santirivera.surveilfall.domain.usecases.base


class UseCaseResponse<Result, Err>// Prevent instances
protected constructor(protected val okResult: Result?, protected val errorResult: Err?) {

    internal val isOkResult: Boolean
        get() = errorResult == null

    internal fun getOkResult(): Result {
        if (!isOkResult) {
            throw IllegalStateException("cannot call to getOkResult if !isOkResult!")
        }
        return okResult!!
    }

    internal fun getErrorResult(): Err {
        if (isOkResult) {
            throw IllegalStateException("cannot call to getErrorResult if isOkResult!")
        }
        if (errorResult is Void) {
            throw RuntimeException("Void has no error!")
        }
        return errorResult!!
    }

    companion object {

        fun <Result, Err> ok(okResult: Result): UseCaseResponse<Result, Err> {
            return UseCaseResponse(okResult, null)
        }

        fun <Result, Err> ok(): UseCaseResponse<Result, Err> {
            return UseCaseResponse(null, null)
        }

        fun <Result, Err> error(errorResult: Err): UseCaseResponse<Result, Err> {
            return UseCaseResponse(null, errorResult)
        }
    }
}

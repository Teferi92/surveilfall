package es.santirivera.surveilfall.domain.usecases.base

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Request> UseCase input Params
 * @param <Result>  UseCase Result OK output
 * @param <Err>     UseCase Result ERROR output
</Err></Result></Request> */
abstract class UseCase<Request, Result, Err : StringErrorOutput> {

    private var mRequestValues: Request? = null

    val tag: String
        get() = javaClass.simpleName

    fun setRequestValues(requestValues: Request) {
        mRequestValues = requestValues
    }

    internal fun run(): UseCaseResponse<Result, Err> {
        return executeUseCase(mRequestValues)
    }

    protected abstract fun executeUseCase(requestValues: Request?): UseCaseResponse<Result, Err>
}

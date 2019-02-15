package es.santirivera.surveilfall.domain.usecases.base

interface UseCaseTaggedCallback<Result, Err : StringErrorOutput> {

    fun onSuccess(tag: String, response: Result)

    fun onError(tag: String, error: Err)

    fun onGenericError(tag: String)

    fun onNetworkUnavailable(tag: String)
}

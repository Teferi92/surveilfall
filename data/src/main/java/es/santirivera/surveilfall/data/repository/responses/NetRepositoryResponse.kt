package es.santirivera.surveilfall.data.repository.responses

open class NetRepositoryResponse<ResponseType>(override val responseData: ResponseType) : RepositoryResponse<ResponseType> {

    override val isSuccess: Boolean
        get() = true

    override val errorCode: Long?
        get() = 0L

    override val errorMessage: String
        get() = ""

}

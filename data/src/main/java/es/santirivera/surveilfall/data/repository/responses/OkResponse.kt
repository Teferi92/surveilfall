package es.santirivera.surveilfall.data.repository.responses

class OkResponse<ResponseType>(override val responseData: ResponseType) : RepositoryResponse<ResponseType> {

    override val isSuccess: Boolean
        get() = true

    override val errorCode: Long?
        get() = throw RuntimeException("OkResponse does not have errorCode!")

    override val errorMessage: String
        get() = throw RuntimeException("OkResponse does not have errorCode!")

}

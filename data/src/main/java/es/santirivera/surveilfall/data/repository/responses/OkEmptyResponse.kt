package es.santirivera.surveilfall.data.repository.responses

class OkEmptyResponse : RepositoryResponse<Void> {

    override val isSuccess: Boolean
        get() = true

    override val responseData: Void
        get() = throw RuntimeException("OkEmptyResponse does not have responseData!")

    override val errorCode: Long?
        get() = throw RuntimeException("OkEmptyResponse does not have errorCode!")

    override val errorMessage: String
        get() = throw RuntimeException("OkEmptyResponse does not have errorMessage!")

}

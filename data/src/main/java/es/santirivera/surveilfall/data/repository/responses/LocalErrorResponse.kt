package es.santirivera.surveilfall.data.repository.responses

class LocalErrorResponse<Type>(override val errorCode: Long?) : RepositoryResponse<Type> {

    override val isSuccess: Boolean
        get() = false

    override val responseData: Type
        get() = throw RuntimeException("NetErrorResponse does not have response data!")

    override val errorMessage: String?
        get() = null

}

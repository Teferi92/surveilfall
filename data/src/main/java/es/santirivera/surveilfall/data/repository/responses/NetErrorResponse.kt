package es.santirivera.surveilfall.data.repository.responses

class NetErrorResponse<ResponseType> : NetRepositoryResponse<ResponseType?>(null) {

    override val isSuccess: Boolean
        get() = false

    override val responseData: ResponseType
        get() = throw RuntimeException("NetErrorResponse does not have response data!")

}

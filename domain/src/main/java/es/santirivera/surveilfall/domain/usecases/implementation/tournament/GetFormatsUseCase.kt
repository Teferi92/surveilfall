package es.santirivera.surveilfall.domain.usecases.implementation.tournament

import es.santirivera.surveilfall.data.model.Format
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse


class GetFormatsUseCase(private val appRepository: AppRepository) : UseCase<Void, GetFormatsUseCase.OkOutput, GetFormatsUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.getFormats()
        return if (response.isSuccess) {
            val formats = response.responseData
            UseCaseResponse.ok(OkOutput(formats!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving sets from Scryfall"))
        }
    }

    class OkOutput(val urls: List<Format>)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
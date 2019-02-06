package es.santirivera.surveilfall.domain.usecases.implementation.artist

import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetArtistNamesUseCase(private val appRepository: AppRepository) : UseCase<Void, GetArtistNamesUseCase.OkOutput, GetArtistNamesUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.getArtistNames()
        return if (response.isSuccess!!) {
            val list = response.responseData
            UseCaseResponse.ok(OkOutput(list!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving artists from Scryfall"))
        }
    }

    class OkOutput(val artists: List<String>)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
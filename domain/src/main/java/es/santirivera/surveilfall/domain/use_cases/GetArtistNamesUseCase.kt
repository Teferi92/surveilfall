package es.santirivera.surveilfall.domain.use_cases

import es.santirivera.surveilfall.data.model.Set
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.use_cases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.use_cases.base.UseCase
import es.santirivera.surveilfall.domain.use_cases.base.UseCaseResponse

class GetArtistNamesUseCase(private val appRepository: AppRepository) : UseCase<Void, GetArtistNamesUseCase.OkOutput, GetArtistNamesUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.artistNames
        return if (response.isSuccess!!) {
            val list = response.responseData
            UseCaseResponse.ok(OkOutput(list!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving sets from Scryfall"))
        }
    }

    class OkOutput(val artists: List<String>)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
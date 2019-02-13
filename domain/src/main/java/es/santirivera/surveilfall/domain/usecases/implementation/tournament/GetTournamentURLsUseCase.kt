package es.santirivera.surveilfall.domain.usecases.implementation.tournament

import es.santirivera.surveilfall.data.model.TournamentURLs
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse


class GetTournamentURLsUseCase(private val appRepository: AppRepository) : UseCase<Void, GetTournamentURLsUseCase.OkOutput, GetTournamentURLsUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.getTournamentURLs()
        return if (response.isSuccess!!) {
            val urls = response.responseData
            UseCaseResponse.ok(OkOutput(urls!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving tournament urls from Github"))
        }
    }

    class OkOutput(val urls: TournamentURLs)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
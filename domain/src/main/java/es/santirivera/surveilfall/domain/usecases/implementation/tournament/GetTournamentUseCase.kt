package es.santirivera.surveilfall.domain.usecases.implementation.tournament

import es.santirivera.surveilfall.data.model.Tournament
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetTournamentUseCase(private val appRepository: AppRepository) : UseCase<GetTournamentUseCase.Input, GetTournamentUseCase.OkOutput, GetTournamentUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Input): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.getTournament(requestValues.format.toLowerCase(), requestValues.date)
        return if (response.isSuccess) {
            val tournament = response.responseData
            UseCaseResponse.ok(OkOutput(tournament!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving tournament from Github"))
        }
    }

    class Input(val format: String, val date: String)

    class OkOutput(val tournament: Tournament)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
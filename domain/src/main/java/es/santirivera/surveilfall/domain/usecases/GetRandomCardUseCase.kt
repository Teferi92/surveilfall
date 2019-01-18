package es.santirivera.surveilfall.domain.usecases

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetRandomCardUseCase(private val appRepository: AppRepository) : UseCase<GetRandomCardUseCase.Input, GetRandomCardUseCase.OkOutput, GetRandomCardUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Input?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.randomCard(requestValues!!.query)
        return if (response.isSuccess!!) {
            val card = response.responseData
            UseCaseResponse.ok(OkOutput(card!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving random card from Scryfall"))
        }
    }

    class Input(val query: String)

    class OkOutput(val card: Card)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}

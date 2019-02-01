package es.santirivera.surveilfall.domain.usecases

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardList
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetReprintsForCardUseCase(private val appRepository: AppRepository) : UseCase<GetReprintsForCardUseCase.Input, GetReprintsForCardUseCase.OkOutput, GetReprintsForCardUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: GetReprintsForCardUseCase.Input): UseCaseResponse<GetReprintsForCardUseCase.OkOutput, GetReprintsForCardUseCase.ErrorOutput> {
        val query = "oracleId:${requestValues.card.oracleId}"
        val response = appRepository.cardsForQuery(
                query, requestValues.page,
                GetCardsForQueryUseCase.PrintsToInclude.PRINTS.str,
                GetCardsForQueryUseCase.SortMethod.RELEASED.value,
                GetCardsForQueryUseCase.SortOrder.AUTO.value
        )
        return if (response.isSuccess!!) {
            val list = response.responseData
            UseCaseResponse.ok(GetReprintsForCardUseCase.OkOutput(list!!))
        } else {
            UseCaseResponse.error(GetReprintsForCardUseCase.ErrorOutput("Error retrieving query from Scryfall"))
        }
    }


    class Input(
            val card: Card,
            val page: Int
    )

    class OkOutput(
            val cardList: CardList
    )

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}

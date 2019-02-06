package es.santirivera.surveilfall.domain.usecases.implementation.cards

import es.santirivera.surveilfall.data.model.CardList
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetCardsForQueryUseCase(private val appRepository: AppRepository) : UseCase<GetCardsForQueryUseCase.Input, GetCardsForQueryUseCase.OkOutput, GetCardsForQueryUseCase.ErrorOutput>() {

    enum class PrintsToInclude(val str: String) {
        CARDS("cards"),
        ART("art"),
        PRINTS("prints")
    }

    enum class SortMethod(val value: String) {
        NAME("name"),
        SET("set"),
        RELEASED("released"),
        RARITY("rarity"),
        COLOR("color"),
        CMC("cmc"),
        POWER("power"),
        TOUGHNESS("toughness"),
        EDHREC("edhrec"),
        ARTIST("artist")
    }

    enum class SortOrder(val value: String) {
        ASC("asc"), DESC("desc"), AUTO("auto")
    }

    override fun executeUseCase(requestValues: Input?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.cardsForQuery(
                requestValues!!.query,
                requestValues.page,
                requestValues.printsToInclude.str,
                requestValues.sortMethod.value,
                requestValues.sortOrder.value
        )
        return if (response.isSuccess!!) {
            val list = response.responseData
            UseCaseResponse.ok(OkOutput(list!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving query from Scryfall"))
        }
    }

    class Input(
            val query: String,
            val page: Int,
            val printsToInclude: PrintsToInclude,
            val sortMethod: SortMethod,
            val sortOrder: SortOrder
    )

    class OkOutput(
            val cardList: CardList
    )

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
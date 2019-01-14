package es.santirivera.surveilfall.domain.use_cases

import es.santirivera.surveilfall.data.model.CardList
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.use_cases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.use_cases.base.UseCase
import es.santirivera.surveilfall.domain.use_cases.base.UseCaseResponse

class GetCardsForQueryUseCase(private val appRepository: AppRepository) : UseCase<GetCardsForQueryUseCase.Input, GetCardsForQueryUseCase.OkOutput, GetCardsForQueryUseCase.ErrorOutput>() {

    enum class PrintsToInclude(val str: String) {
        CARDS("cards"), ART("art"), PRINTS("prints")
    }

    override fun executeUseCase(requestValues: Input?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.cardsForQuery(requestValues!!.query, requestValues.page, requestValues.printsToInclude.str);
        return if (response.isSuccess!!) {
            val list = response.responseData
            UseCaseResponse.ok(OkOutput(list!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving sets from Scryfall"))
        }
    }

    class Input(val query: String, val page: Int, val printsToInclude: PrintsToInclude)

    class OkOutput(val cardList: CardList)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
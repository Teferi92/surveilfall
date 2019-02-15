package es.santirivera.surveilfall.domain.usecases.implementation.cards

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Identifier
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetCardCollectionUseCase(val appRepository: AppRepository) : UseCase<GetCardCollectionUseCase.Input, GetCardCollectionUseCase.OkOutput, GetCardCollectionUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Input?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.getCardCollection(requestValues!!.identifiers)
        return if (response.isSuccess) {
            val list = response.responseData
            UseCaseResponse.ok(OkOutput(list!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving query from Scryfall"))
        }
    }

    class Input(
            val identifiers: List<Identifier>
    )

    class OkOutput(
            val cardList: List<Card>
    )

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
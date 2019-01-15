package es.santirivera.surveilfall.domain.usecases

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetCardInSetUseCase(private val appRepository: AppRepository) : UseCase<GetCardInSetUseCase.Input, GetCardInSetUseCase.OkOutput, GetCardInSetUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Input?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.cardInSet(requestValues!!.setCode, requestValues.cardInSet)
        return if (response.isSuccess!!) {
            val card = response.responseData
            UseCaseResponse.ok(OkOutput(card!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving sets from Scryfall"))
        }
    }

    class Input(val setCode: String, val cardInSet: Int)

    class OkOutput(val card: Card)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}

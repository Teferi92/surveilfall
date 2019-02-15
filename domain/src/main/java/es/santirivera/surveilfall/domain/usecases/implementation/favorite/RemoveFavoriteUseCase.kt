package es.santirivera.surveilfall.domain.usecases.implementation.favorite

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class RemoveFavoriteUseCase(private val dbRepository: DBRepository) : UseCase<RemoveFavoriteUseCase.Input, RemoveFavoriteUseCase.OkOutput, RemoveFavoriteUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: RemoveFavoriteUseCase.Input?): UseCaseResponse<RemoveFavoriteUseCase.OkOutput, RemoveFavoriteUseCase.ErrorOutput> {
        dbRepository.removeFavorite(requestValues!!.card)
        return UseCaseResponse.ok()
    }

    class Input(val card: Card)

    class OkOutput

    class ErrorOutput(str: String) : StringErrorOutput(str)
}

package es.santirivera.surveilfall.domain.usecases.implementation.favorite

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class AddFavoriteUseCase(private val dbRepository: DBRepository) : UseCase<AddFavoriteUseCase.Input, AddFavoriteUseCase.OkOutput, AddFavoriteUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: AddFavoriteUseCase.Input?): UseCaseResponse<AddFavoriteUseCase.OkOutput, AddFavoriteUseCase.ErrorOutput> {
        dbRepository.addFavorite(requestValues!!.card)
        return UseCaseResponse.ok()
    }

    class Input(val card: Card)

    class OkOutput

    class ErrorOutput(str: String) : StringErrorOutput(str)
}

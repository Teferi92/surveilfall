package es.santirivera.surveilfall.domain.usecases.implementation.favorite

import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class IsFavoriteUseCase(private val dbRepository: DBRepository) : UseCase<IsFavoriteUseCase.Input, IsFavoriteUseCase.OkOutput, IsFavoriteUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: IsFavoriteUseCase.Input?): UseCaseResponse<OkOutput, ErrorOutput> {
        return UseCaseResponse.ok(OkOutput(dbRepository.isFavorite(requestValues!!.cardId)))
    }

    class Input(val cardId: String)
    class OkOutput(val isFavorite: Boolean)
    class ErrorOutput(str: String) : StringErrorOutput(str)
}

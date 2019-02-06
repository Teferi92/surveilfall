package es.santirivera.surveilfall.domain.usecases.implementation.favorite

import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class ClearFavoritesUseCase(private val dbRepository: DBRepository) : UseCase<Void, ClearFavoritesUseCase.OkOutput, ClearFavoritesUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<ClearFavoritesUseCase.OkOutput, ClearFavoritesUseCase.ErrorOutput> {
        dbRepository.clearFavorites()
        return UseCaseResponse.ok()
    }

    class ErrorOutput(str: String) : StringErrorOutput(str)
    class OkOutput
}
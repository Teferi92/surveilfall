package es.santirivera.surveilfall.domain.usecases.implementation.favorite

import es.santirivera.surveilfall.data.model.Favorite
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetFavoritesUseCase(private val dbRepository: DBRepository) : UseCase<Void, GetFavoritesUseCase.OkOutput, GetFavoritesUseCase.ErrorOutput>() {

    class OkOutput(val favorites: List<Favorite>)

    class ErrorOutput(str: String) : StringErrorOutput(str)

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<GetFavoritesUseCase.OkOutput, GetFavoritesUseCase.ErrorOutput> {
        return UseCaseResponse.ok(OkOutput(dbRepository.getFavorites().responseData!!))
    }

}

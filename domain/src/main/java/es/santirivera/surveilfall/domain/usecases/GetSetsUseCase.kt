package es.santirivera.surveilfall.domain.usecases

import es.santirivera.surveilfall.data.model.Set
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetSetsUseCase(private val appRepository: AppRepository) : UseCase<Void, GetSetsUseCase.OkOutput, GetSetsUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<GetSetsUseCase.OkOutput, GetSetsUseCase.ErrorOutput> {
        val response = appRepository.setList
        return if (response.isSuccess!!) {
            val list = response.responseData?.data
            UseCaseResponse.ok(OkOutput(list!!))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving sets from Scryfall"))
        }
    }

    class OkOutput(val sets: List<Set>)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
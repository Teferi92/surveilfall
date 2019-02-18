package es.santirivera.surveilfall.domain.usecases.implementation.wordbank

import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class ClearWordBankUseCase(private val dbRepository: DBRepository) : UseCase<Void, ClearWordBankUseCase.OkOutput, ClearWordBankUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<ClearWordBankUseCase.OkOutput, ClearWordBankUseCase.ErrorOutput> {
        dbRepository.clearWordBank()
        return UseCaseResponse.ok(OkOutput())
    }

    class OkOutput
    class ErrorOutput(str: String) : StringErrorOutput(str)

}
package es.santirivera.surveilfall.domain.usecases.implementation.wordbank

import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetWordBankUseCase(private val dbRepository: DBRepository) : UseCase<Void, GetWordBankUseCase.OkOutput, GetWordBankUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = dbRepository.getWordBank()

        val list = response.responseData
        val worldBankList = ArrayList<WordBankItem>()
        for (item in (list as List<String>)) {
            worldBankList.add(WordBankItem(item))
        }
        return UseCaseResponse.ok(OkOutput(worldBankList))

    }

    class OkOutput(val words: List<WordBankItem>)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}

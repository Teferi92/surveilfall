package es.santirivera.surveilfall.domain.usecases

import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class GetWordBankUseCase(private val appRepository: AppRepository) : UseCase<Void, GetWordBankUseCase.OkOutput, GetWordBankUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.wordBank
        return if (response.isSuccess!!) {
            val list = response.responseData
            val worldBankList = ArrayList<WordBankItem>()
            for (item in (list as List<String>)) {
                worldBankList.add(WordBankItem(item))
            }
            UseCaseResponse.ok(OkOutput(worldBankList))
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving word bank from Scryfall"))
        }
    }

    class OkOutput(val words: List<WordBankItem>)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
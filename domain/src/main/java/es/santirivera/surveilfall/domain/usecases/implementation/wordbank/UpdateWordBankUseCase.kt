package es.santirivera.surveilfall.domain.usecases.implementation.wordbank

import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse

class UpdateWordBankUseCase(private val appRepository: AppRepository, private val dbRepository: DBRepository) : UseCase<Void, UpdateWordBankUseCase.OkOutput, UpdateWordBankUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Void?): UseCaseResponse<OkOutput, ErrorOutput> {
        val response = appRepository.getWordBank()
        return if (response.isSuccess) {
            val list = response.responseData
            val worldBankList = ArrayList<WordBankItem>()
            for (item in (list as List<String>)) {
                worldBankList.add(WordBankItem(item))
            }
            dbRepository.clearWordBank()
            dbRepository.updateWordBank(worldBankList)
            UseCaseResponse.ok(OkOutput())
        } else {
            UseCaseResponse.error(ErrorOutput("Error retrieving word bank from Scryfall"))
        }
    }

    class OkOutput

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)

}
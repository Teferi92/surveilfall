package es.santirivera.surveilfall.domain.use_cases.providers

import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.use_cases.GetSetsUseCase

class UseCaseProvider(private val appRepository: AppRepository) {

    val getSetsUseCase: GetSetsUseCase
        get() = GetSetsUseCase(appRepository)

}
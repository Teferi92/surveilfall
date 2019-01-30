package es.santirivera.surveilfall.domain.usecases

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import es.santirivera.surveilfall.domain.usecases.base.StringErrorOutput
import es.santirivera.surveilfall.domain.usecases.base.UseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCaseResponse
import java.net.URL

class GetBitmapFromURLUseCase() : UseCase<GetBitmapFromURLUseCase.Input, GetBitmapFromURLUseCase.OkOutput, GetBitmapFromURLUseCase.ErrorOutput>() {

    override fun executeUseCase(requestValues: Input): UseCaseResponse<OkOutput, ErrorOutput> {
        val bitmap: Bitmap?
        try {
            val inputStream = URL(requestValues.url).openStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (e: Exception) {
            return UseCaseResponse.error(ErrorOutput(e.message!!))
        }
        return UseCaseResponse.ok(OkOutput(bitmap))
    }

    class Input(val url: String)

    class OkOutput(val bitmap: Bitmap)

    class ErrorOutput(errorDesc: String) : StringErrorOutput(errorDesc)
}

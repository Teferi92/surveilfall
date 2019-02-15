package es.santirivera.surveilfall.domain.usecases.base

import android.util.Log

abstract class UseCasePartialCallback<Ok, Error : StringErrorOutput> : UseCaseTaggedCallback<Ok, Error> {

    override fun onError(tag: String, error: Error) {
        Log.e("onError", error.errorDesc)
    }

    override fun onGenericError(tag: String) {
        Log.e("onGenericError", tag)
    }

    override fun onNetworkUnavailable(tag: String) {
        Log.e("onNetworkUnavailable", tag)
    }
}

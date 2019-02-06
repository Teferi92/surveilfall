package es.santirivera.surveilfall.domain.usecases.base;

import android.util.Log;

public abstract class UseCasePartialCallback<Ok, Error extends StringErrorOutput> implements UseCaseTaggedCallback<Ok, Error> {

    @Override
    public void onError(String tag, Error error) {
        Log.e("onError", error.getErrorDesc());
    }

    @Override
    public void onGenericError(String tag) {
        Log.e("onGenericError", tag);
    }

    @Override
    public void onNetworkUnavailable(String tag) {
        Log.e("onNetworkUnavailable", tag);
    }

    @Override
    public boolean isReady() {
        return true;
    }
}

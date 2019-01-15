package es.santirivera.surveilfall.domain.usecases.base;

public abstract class UseCasePartialCallback<Ok, Error extends StringErrorOutput> implements UseCaseTaggedCallback<Ok, Error> {

    @Override
    public void onError(String tag, Error error) {

    }

    @Override
    public void onGenericError(String tag) {

    }

    @Override
    public void onNetworkUnavailable(String tag) {

    }

}

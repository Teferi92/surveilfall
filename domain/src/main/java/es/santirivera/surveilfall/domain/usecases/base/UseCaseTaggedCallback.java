package es.santirivera.surveilfall.domain.usecases.base;

public interface UseCaseTaggedCallback<Result, Err extends StringErrorOutput> {

	boolean isReady();

	void onSuccess(String tag, Result response);

	void onError(String tag, Err error);

	void onGenericError(String tag);

	void onNetworkUnavailable(String tag);
}

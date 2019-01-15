package es.santirivera.surveilfall.domain.usecases.base;

import androidx.annotation.Nullable;

public class StringErrorOutput {

	private final String errorDesc;

	public StringErrorOutput(@Nullable String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Nullable
	public String getErrorDesc() {
		return errorDesc;
	}
}

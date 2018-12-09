package es.aespinilla.software.android.finofilipinoclient.listeners;

import es.aespinilla.software.android.finofilipinoclient.models.error.FinoError;

public interface FinoCallback<T> {
    void onSucessResponse(T r);
    void onErrorResponse(FinoError finoError);
    void onFailureError(Throwable t);
}

package com.bridgelabz.BookStoreBackend.exception;

import com.bridgelabz.BookStoreBackend.util.ErrorResponse;
import com.bridgelabz.BookStoreBackend.util.Response;
import lombok.Data;

import java.util.Locale;

@Data
public class UserRegisteredException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private int StatusCode;
    private String Statusmessage;

    public UserRegisteredException(int statusCode, String statusmessage) {
        super(statusmessage);
        StatusCode = statusCode;
        Statusmessage = statusmessage;
    }

    public Response getErrorResponse() {
        return getErrorResponse(Locale.getDefault());
    }

    public Response getErrorResponse(Locale locale) {

        ErrorResponse errorResp = new ErrorResponse(StatusCode, Statusmessage, getStackTrace());
        errorResp.setStatusCode(getStatusCode());
        errorResp.setStatusMessage(getStatusmessage());

        return errorResp;
    }
}

package com.vet.commons.exceptions;

public class ThrowableRestExeption extends Throwable {
    private Exception exceptionBody;
    public ThrowableRestExeption (Exception e) {
        exceptionBody = e;
    }
    public Exception getExceptionBody() {
        return exceptionBody;
    }

}

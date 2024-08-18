package com.vet.commons.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class MongoDuplicateKeyException extends DuplicateKeyException{

    public MongoDuplicateKeyException(String msg) {
        super(msg);
    }
}

package com.silverpeas.components.oosphere.blankCmp.web;

import com.silverpeas.components.oosphere.blankCmp.exception.BlankCmpRuntimeException;

public abstract class BlankCmpWebException extends BlankCmpRuntimeException {

    public BlankCmpWebException(String message) {
        super(message);
    }

    public BlankCmpWebException(Exception cause) {
        super(cause);
    }

}

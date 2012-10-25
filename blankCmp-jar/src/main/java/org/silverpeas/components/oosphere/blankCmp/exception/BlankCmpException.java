package org.silverpeas.components.oosphere.blankCmp.exception;

import org.silverpeas.components.oosphere.blankCmp.BlankCmpConstants;
import com.stratelia.webactiv.util.exception.SilverpeasException;

public class BlankCmpException extends SilverpeasException {

	public BlankCmpException(String message) {
        super(null, SilverpeasException.ERROR, message, null, null);
    }

    public BlankCmpException(String message, Exception cause) {
        super(null, SilverpeasException.ERROR, message, null, cause);
    }

    @Override
    public final String getModule() {
        return BlankCmpConstants.SILVERTRACE_MODULE_NAME;
    }
}

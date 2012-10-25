/**
 * 
 */
package org.silverpeas.components.oosphere.blankCmp.exception;

import org.silverpeas.components.oosphere.blankCmp.BlankCmpConstants;
import com.stratelia.webactiv.util.exception.SilverpeasException;
import com.stratelia.webactiv.util.exception.SilverpeasRuntimeException;

public class BlankCmpRuntimeException extends SilverpeasRuntimeException {

	public BlankCmpRuntimeException(String message) {
        super(null, SilverpeasException.ERROR, message, null, null);
    }

	public BlankCmpRuntimeException(Exception cause) {
        super(null, SilverpeasException.ERROR, cause.getMessage(), null, cause);
    }

    @Override
    public final String getModule() {
        return BlankCmpConstants.SILVERTRACE_MODULE_NAME;
    }
}

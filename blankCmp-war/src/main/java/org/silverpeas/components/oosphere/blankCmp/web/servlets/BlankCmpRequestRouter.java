/**
 * Copyright (C) 2000 - 2011 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.com/legal/licensing"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.components.oosphere.blankCmp.web.servlets;

import org.silverpeas.components.oosphere.blankCmp.web.control.BlankCmpSessionController;
import org.silverpeas.webcore.Action;
import org.silverpeas.webcore.RequestRouterSupport;
import com.stratelia.silverpeas.peasCore.ComponentContext;
import com.stratelia.silverpeas.peasCore.MainSessionController;

public class BlankCmpRequestRouter extends RequestRouterSupport<BlankCmpSessionController> {
	private static final long serialVersionUID = 2306409242623119934L;

	public BlankCmpRequestRouter() {
		super();
	}

    /**
	 * This method has to be implemented in the component request rooter class.
	 * returns the session control bean name to be put in the request object ex
	 * : for almanach, returns "almanach"
	 */
	@Override
	public String getSessionControlBeanName() {
		return "blankCmp";
	}

	@Override
	public BlankCmpSessionController createComponentSessionController(
			MainSessionController mainSessionCtrl,
			ComponentContext componentContext) {
		return new BlankCmpSessionController(mainSessionCtrl, componentContext);
	}

    @Override
    protected Action resolveAction(String action) {
        for(BlankCmpAction a:BlankCmpAction.values()){
            if(a.getURI().equals(action))
                return a;
        }
        return null;
    }
}

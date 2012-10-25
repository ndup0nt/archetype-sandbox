package org.silverpeas.components.oosphere.blankCmp.web.servlets;

import org.silverpeas.webcore.ActionHandler;
import org.silverpeas.webcore.ActionHandlerSupport;

import javax.servlet.http.HttpServletRequest;

public class CreateHandler extends ActionHandlerSupport implements
        ActionHandler {

    @Override
    public String resolveView(final HttpServletRequest request) {
        return "create.jsp";
    }

}

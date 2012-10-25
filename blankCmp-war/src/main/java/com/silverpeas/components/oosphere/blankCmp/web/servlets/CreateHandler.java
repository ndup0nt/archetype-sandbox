package com.silverpeas.components.oosphere.blankCmp.web.servlets;

import com.silverpeas.webcore.ActionHandler;
import com.silverpeas.webcore.ActionHandlerSupport;

import javax.servlet.http.HttpServletRequest;

public class CreateHandler extends ActionHandlerSupport implements
        ActionHandler {

    @Override
    public String resolveView(final HttpServletRequest request) {
        return "create.jsp";
    }

}

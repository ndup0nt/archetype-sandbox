package com.silverpeas.components.oosphere.blankCmp.web.servlets;

import javax.servlet.http.HttpServletRequest;

import com.silverpeas.webcore.ActionHandler;
import com.silverpeas.webcore.ActionHandlerSupport;

public class MainHandler extends ActionHandlerSupport implements
        ActionHandler {

    @Override
    public String resolveView(final HttpServletRequest request) {
        return "welcome.jsp";
    }

}

package com.silverpeas.components.oosphere.blankCmp.web.servlets;

import javax.servlet.http.HttpServletRequest;

import com.silverpeas.components.oosphere.blankCmp.service.BlankCmpServicesLocator;
import com.silverpeas.webcore.ActionHandler;
import com.silverpeas.webcore.ActionHandlerSupport;

public class MainHandler extends ActionHandlerSupport implements
        ActionHandler {

    @Override
    public String resolveView(final HttpServletRequest request) {
        request.setAttribute("objects", BlankCmpServicesLocator.getInstance().getBlankStuffService().getAllBlankStuffs());
        return "welcome.jsp";
    }

}

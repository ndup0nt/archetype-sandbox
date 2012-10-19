package com.silverpeas.webcore;

import javax.servlet.http.HttpServletRequest;

public interface ActionHandler {
    /**
     * @param request the HTTP request sent to the Servlet
     * @return the name of the view (likely to be a JSP name)
     */
	public String resolveView(HttpServletRequest request);
}

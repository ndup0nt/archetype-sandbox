package com.silverpeas.components.oosphere.blankCmp.web.servlets;

import com.silverpeas.webcore.Action;
import com.silverpeas.webcore.ActionHandlerSupport;

public enum BlankCmpAction implements Action {
    MAIN("Main", new MainHandler()),
    CREATE("Create", new CreateHandler());
    
    private final ActionHandlerSupport handler;
    private final String action;

    BlankCmpAction(String action, ActionHandlerSupport handler){
        this.action = action;
        this.handler = handler;
    }

    @Override
    public ActionHandlerSupport getHandler(){
        return handler;
    }

    @Override
	public String getURI() {
		return action;
	}
}

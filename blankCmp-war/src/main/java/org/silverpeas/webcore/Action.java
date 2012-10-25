package org.silverpeas.webcore;


public interface Action {
    /**
     *
     * @return an handler for the action. Should not be null
     */
    ActionHandlerSupport getHandler();
    
    String getURI();
}

package org.silverpeas.webcore;

import com.stratelia.silverpeas.peasCore.ComponentSessionController;

public abstract class ActionHandlerSupport implements ActionHandler {
    private ComponentSessionController controller;

    protected ComponentSessionController getComponentSessionController() {
        return controller;
    }

    void setSessionController(ComponentSessionController controller) {
        this.controller = controller;
    }
}

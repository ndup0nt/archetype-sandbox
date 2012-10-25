package org.silverpeas.webcore;

import javax.servlet.http.HttpServletRequest;

import com.stratelia.silverpeas.peasCore.ComponentContext;
import com.stratelia.silverpeas.peasCore.ComponentSessionController;
import com.stratelia.silverpeas.peasCore.MainSessionController;
import com.stratelia.silverpeas.peasCore.PeasCoreRuntimeException;
import com.stratelia.silverpeas.peasCore.servlets.ComponentRequestRouter;
import com.stratelia.silverpeas.silvertrace.SilverTrace;
import com.stratelia.webactiv.util.exception.SilverpeasRuntimeException;

public abstract class RequestRouterSupport<SC extends ComponentSessionController> extends ComponentRequestRouter<SC> {
	private static final long serialVersionUID = 9146669532231580837L;

	public RequestRouterSupport() {
        super();
    }

    @Override
    public abstract String getSessionControlBeanName();

    @Override
    public abstract SC createComponentSessionController(
            MainSessionController mainSessionCtrl,
            ComponentContext componentContext);

    private String getDestination(String function,
                                    SC componentSC, HttpServletRequest request,
			boolean isRedirect) {
        Action action = resolveAction(function);
        if(action == null){
            throw new PeasCoreRuntimeException(
                    RequestRouterSupport.class.getSimpleName()+".getDestination()",
                    SilverpeasRuntimeException.ERROR, "Cannot find handler for " + function);
        }
        ActionHandlerSupport actionHandler = initHandler(action, componentSC);
		String view = resolveView(actionHandler, componentSC, request, isRedirect);
        request.setAttribute("action", action);
		return view;
	}

    private String resolveView(
			ActionHandlerSupport actionHandler,
            SC componentSC, HttpServletRequest request,
			boolean isRedirect) {
        String view = actionHandler.resolveView(request);
        if (view == null) {
			throw new PeasCoreRuntimeException(
                    RequestRouterSupport.class.getSimpleName()+".getDestination()",
					SilverpeasRuntimeException.ERROR, "Handler "
							+ actionHandler.getClass().getName()
							+ " can't resolve view");
		}
		if (view.startsWith("redirect:")) {
			if (isRedirect) {
				throw new PeasCoreRuntimeException(
                        RequestRouterSupport.class.getSimpleName()+".getDestination()",
						SilverpeasRuntimeException.ERROR,
						"Circular redirection");
			}
			return getDestination(view.substring("redirect:".length()),
					componentSC, request, true);
		}

		return "/"+componentSC.getComponentName()+"/jsp/" + view;
	}

    private ActionHandlerSupport initHandler(Action action, SC componentSC) {
        ActionHandlerSupport actionHandler = action.getHandler();
        if(actionHandler == null){
            throw new PeasCoreRuntimeException(
                    RequestRouterSupport.class.getSimpleName()+".getDestination()",
                    SilverpeasRuntimeException.ERROR, "Handler for action " + action
                    + " should not be null");
        }
        actionHandler.setSessionController(componentSC);
        return actionHandler;
    }

    /**
     * This method has to be implemented by the component request rooter it has
     * to compute a destination page
     *
     * @param function
     *            The entering request function (ex : "Main.jsp")
     * @param componentSC
     *            The component Session Control, build and initialised.
     * @return The complete destination URL for a forward (ex :
     *         "/almanach/jsp/almanach.jsp?flag=user")
     */
    @Override
    public String getDestination(String function,
                                 SC componentSC, HttpServletRequest request) {
        SilverTrace.info(componentSC.getComponentName(), RequestRouterSupport.class.getSimpleName()+".getDestination()",
                "root.MSG_GEN_PARAM_VALUE", "User=" + componentSC.getUserId()
                + " Function=" + function);
        try {
            String destination = getDestination(function, componentSC, request,
                    false);
            SilverTrace.info(componentSC.getComponentName(),
                    RequestRouterSupport.class.getSimpleName()+".getDestination()",
                    "root.MSG_GEN_PARAM_VALUE", "Destination=" + destination);
            return destination;
        } catch (RuntimeException e) {
            SilverTrace.error(componentSC.getComponentName(),
                    RequestRouterSupport.class.getSimpleName()+".getDestination()",
                    "root.MSG_GEN_PARAM_VALUE",e);
            request.setAttribute("javax.servlet.jsp.jspException", e);
            return "/admin/jsp/errorpage.jsp";
        }
    }

    protected abstract Action resolveAction(String function);
}

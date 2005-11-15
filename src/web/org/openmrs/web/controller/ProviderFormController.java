package org.openmrs.web.controller;

import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.UserService;
import org.openmrs.context.Context;
import org.openmrs.web.Constants;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class ProviderFormController extends SimpleFormController {
	
    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 
	 * This is called prior to displaying a form for the first time.  It tells Spring
	 *   the form/command object to load into the request
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {

		HttpSession httpSession = request.getSession();
		Context context = (Context) httpSession.getAttribute(Constants.OPENMRS_CONTEXT_HTTPSESSION_ATTR);
		
		List userList = null;
		if (context != null && context.isAuthenticated()) {
			UserService us = context.getUserService();
	    	userList = us.getUsersByRole(us.getRole("provider"));	
		}
		
		if (userList == null)
			userList = new Vector();
    	
        return userList;
    }
    
}
package org.aeopensolutions.model.utils;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.aeopensolutions.model.ejb.facades.AdUserFacade;
import org.aeopensolutions.model.entities.AdUser;

@Stateless
public class SecurityUtils {   
	
	@Resource
	private SessionContext session;
	
	@Inject
	private AdUserFacade adUserFacade;

	public String getCurrentUser() {

		String currentUser = null;
		
		currentUser = session.getCallerPrincipal().getName();

		System.out.println("audit: " + currentUser);

		
		return currentUser;

	}
	
	public AdUser getAdUser(){
		return adUserFacade.findByUsername(getCurrentUser());
	}
	

}

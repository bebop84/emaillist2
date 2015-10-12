package com.bit2015.emaillist.web.action;

import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

public class EmailListActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if( "form".equals(actionName) ) {
			action = new FormAction();
		} else if( "add".equals( actionName ) ) {
			action = new AddAction();
		} else {
			action = new ShowAction();
		}
		
		return action;
	}

}

package at.jku.ce.cep.ui;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

import at.jku.ce.cep.ui.login.LoginView;
import at.jku.ce.cep.ui.navigator.NavigatorView;

public class ApplicationView extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5798163901372154343L;
	
	private NavigatorView navigatorView = null;
	public LoginView loginView= null;
	public ComponentContainer mainComponentContainer = null;
	
	public ApplicationView() {
		super();
		init();
	}
	
	private void init() {
		this.setSizeFull();
		this.addComponent(getNavigatorView());		
		//this.addComponent(getMainComponentContainer());	
	}
	
	public NavigatorView getNavigatorView() {
		if(navigatorView == null) {
			navigatorView = new NavigatorView();
		}
		return navigatorView;
	}
	
	public ComponentContainer getMainComponentContainer() {
		if(mainComponentContainer == null) {
			mainComponentContainer = new CssLayout();
			mainComponentContainer.addStyleName("view-content");
			mainComponentContainer.setSizeFull();
		}
		return mainComponentContainer;
	}

}

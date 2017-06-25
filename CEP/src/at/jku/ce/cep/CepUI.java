package at.jku.ce.cep;

import javax.servlet.annotation.WebServlet;

import org.eclipse.viatra.cep.core.api.engine.CEPEngine;
import org.eclipse.viatra.cep.core.metamodels.automaton.EventContext;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import at.jku.ce.cep.beans.User;
import at.jku.ce.cep.engine.model.CepFactory;
import at.jku.ce.cep.ui.ApplicationController;
import at.jku.ce.cep.ui.events.LoginEvent;
import at.jku.ce.cep.ui.events.LogoutEvent;
import at.jku.ce.cep.ui.login.LoginController;

@SuppressWarnings("serial")
@Theme("cep")
@Title("CEP")
public class CepUI extends UI {
	private static User user;
	private LoginController loginController = null;
	private ApplicationController applicationController = null;
	private static EventBus eventBus = null;
	private CEPEngine engine = null;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = CepUI.class, widgetset = "at.jku.ce.cep.widgetset.CepWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		Page.getCurrent().addBrowserWindowResizeListener(new BrowserWindowResizeListener() {

			@Override
			public void browserWindowResized(BrowserWindowResizeEvent event) {
				// use to inform component which need manual resizing

			}
		});
		
		// set the UI size to max
		setSizeFull();

		// initialize the grip entity manager with the factory (takes some time to load)
		//CEPEntityManager.getInstance();

		// register this class to the eventbus
		eventBus = new EventBus();
		eventBus.register(this);
		
		// initialize cep engine
//		buildEngine();

		switchBySession();
//				
//		final VerticalLayout layout = new VerticalLayout();
//		layout.setMargin(true);
//		setContent(layout);
//
//		Button button = new Button("Click Me");
//		button.addClickListener(new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//				layout.addComponent(new Label("Thank you for clicking"));
//			}
//		});
//		layout.addComponent(button);
	}
	
	/**
	 * Get the EventBus for the current session.
	 * 
	 * @return
	 */
	public static EventBus getEventBus() {
		return eventBus;
	}
	
	/**
	 * Get the current logged on user.
	 * 
	 * @return
	 */
	public static User getUser() {
		return user;
	}
	
	@Subscribe
	public void listenLogin(LoginEvent event){
		// Log Event
		System.out.println("Login Event arrived: Login "
				+ (event.isValidLogin() ? "accepted" : "declined."));
		
		if(event.isValidLogin()){
			user = event.getUser();
			// put User into current session
			VaadinSession.getCurrent().setAttribute(User.class.getName(), user);
			// start the application
			switchToApplication();			
		}
	}

	@Subscribe
	public void listenLogout(LogoutEvent event) {
		// remove User from current session
		VaadinSession.getCurrent().setAttribute(User.class.getName(), null);
		switchToLogin();
	}
	
	/**
	 * Decide if the login or application view has to be shown whether the user is already logged in.
	 */
	private void switchBySession() {
		User user = (User) VaadinSession.getCurrent().getAttribute(
                User.class.getName());
        if (user != null) {
        	switchToApplication();
        } else {
        	switchToLogin();
        }
	}

	private void switchToApplication() {
		applicationController = new ApplicationController();
		setContent(applicationController.getView());
		loginController = null;
	}
	
	private void switchToLogin() {
		user = null;
		loginController = new LoginController();
		setContent(loginController.getView());
		
		// focus username text field (after view was attached)
		loginController.getView().getUsernameTextField().focus();

		applicationController = null;
	}
	
	private void buildEngine() {
		engine = CEPEngine.newEngine().eventContext(EventContext.CHRONICLE).rules(CepFactory.getInstance().allRules())
				.prepare();
	}

}
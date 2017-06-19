package at.jku.ce.cep.ui;

import com.google.common.eventbus.Subscribe;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import at.jku.ce.cep.CepUI;
import at.jku.ce.cep.ui.event.complex.ComplexEventController;
import at.jku.ce.cep.ui.event.simple.SimpleEventController;
import at.jku.ce.cep.ui.events.LogoutEvent;
import at.jku.ce.cep.ui.overview.OverviewController;

public class ApplicationController {
	private SimpleEventController simpleEventController = null;
	private ComplexEventController complexEventController = null;
//	private UserController usersController = null;
//	private DrawboardController drawboardController = null; 
	private OverviewController overviewController = null;
	private Component actComponent = null; 

	private ApplicationView view = null;
	
	public ApplicationController() {
		view = new ApplicationView();
		init();
	}
	
	private void init(){
		// register this instance to the eventbus
		CepUI.getEventBus().register(this);
		
		// set user to the navigation
		view.getNavigatorView().getUserLabel().setValue("User:"
				+ " <span class=\"valo-menu-badge\">" + CepUI.getUser().getUsername()
                + "</span>");
		
		// switch to initial panel
		//switchToOverview();
		
		// add listener
		view.getNavigatorView().getOverviewButton().addClickListener(this::overview);
		view.getNavigatorView().getCepButton().addClickListener(this::complexEvents);
		view.getNavigatorView().getSimpleEventsButton().addClickListener(this::simpleEvents);
		view.getNavigatorView().getUsersButton().addClickListener(this::users);
		view.getNavigatorView().getSignOutButton().addClickListener(this::signOut);
	}
	
	/* LISTENERS */
	private void overview (Button.ClickEvent event){
		switchToOverview();
	}
	
	private void complexEvents(Button.ClickEvent event) {
		switchToComplexEvents();
	}
	
	private void simpleEvents(Button.ClickEvent event) {
		switchToSimpleEvents();
	}
	
	private void users(Button.ClickEvent event) {
//		switchToUsers();
	}
	
	private void signOut(Button.ClickEvent event) {
		// save Notes before logout
//		if(overviewController != null) {
//			overviewController.saveNote();
//		}
		// send logout event on eventbus
		CepUI.getEventBus().post(new LogoutEvent());
	}
	
	// Subscribe to eventbus
//	@Subscribe
//	public void listenOpenDrawing(OpenDrawingEvent event) {
//		switchToDrawboard(event.getBean());
//	}
	
	/**
	 * remove current component from view and start/switch to overview panel
	 */
	private void switchToOverview(){
		if(overviewController == null){
			overviewController = new OverviewController();			
		}
		if(actComponent != null){
			view.removeComponent(actComponent);
		}
		actComponent = overviewController.getView();
		view.addComponent(actComponent);
		view.setExpandRatio(actComponent, 1.0f);
	}
	
//	/**
//	 * remove current component from view and start/switch to drawboard panel
//	 */
//	private void switchToDrawboard(Drawing bean){
//		if(drawboardController == null){
//			drawboardController = new DrawboardController(bean);			
//		} else {
//			drawboardController.setDrawing(bean);
//		}
//		if(actComponent != null){
//			view.removeComponent(actComponent);
//		}
//		actComponent = drawboardController.getView();
//		view.addComponent(actComponent);
//		view.setExpandRatio(actComponent, 1.0f);
//	}
	
	/**
	 * remove current component from view and start/switch to simple events panel
	 */
	private void switchToSimpleEvents(){
		if(simpleEventController == null){
			simpleEventController = new SimpleEventController();			
		}
		if(actComponent != null){
			view.removeComponent(actComponent);
		}
		actComponent = simpleEventController.getView();
		view.addComponent(actComponent);
		view.setExpandRatio(actComponent, 1.0f);
	}
	
	/**
	 * remove current component from view and start/switch to complex events panel
	 */
	private void switchToComplexEvents(){
		if(complexEventController == null){
			complexEventController = new ComplexEventController();			
		}
		if(actComponent != null){
			view.removeComponent(actComponent);
		}
		actComponent = complexEventController.getView();
		view.addComponent(actComponent);
		view.setExpandRatio(actComponent, 1.0f);
	}
	
//	/**
//	 * remove current component from view and start/switch to users panel
//	 */
//	private void switchToUsers(){
//		if(usersController == null){
//			usersController = new UserController();			
//		}
//		if(actComponent != null){
//			view.removeComponent(actComponent);
//		}
//		actComponent = usersController.getView();
//		view.addComponent(actComponent);
//		view.setExpandRatio(actComponent, 1.0f);
//	}
	
	/**
	 * Getter for the panel view
	 * 
	 * @return
	 */
	public ApplicationView getView(){
		return view;
	}
	
}

package at.jku.ce.cep.ui.navigator;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class NavigatorView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3516870999995791498L;
	
	public static final String ID = "dashboard-menu";
	private static final String BUTTON_WIDTH = "180px";
	
	private VerticalLayout menuVerticalLayout = null;
	private HorizontalLayout logoWrapperHorizontalLayout = null;
	private Button overviewButton = null;
	private Button cepButton = null;
	private Button simpleEventsButton = null;
	private Button usersButton = null;
	private Button signOutButton = null;
	private Label userLabel = null;
	
	public NavigatorView() {
		super();
		init();
	}
	
	private void init() {
		this.addStyleName(ValoTheme.MENU_ROOT);
		this.setSizeUndefined();
		this.setId(ID);
		this.setCompositionRoot(getMenuVerticalLayout());
	}
	
	public VerticalLayout getMenuVerticalLayout() {
		if(menuVerticalLayout == null) {
			menuVerticalLayout = new VerticalLayout();
			menuVerticalLayout.addStyleName(ValoTheme.MENU_PART);
			menuVerticalLayout.addStyleName("no-vertical-drag-hints");
			menuVerticalLayout.addStyleName("no-horizontal-drag-hints");
			menuVerticalLayout.setWidth(null);
			menuVerticalLayout.setHeight("100%");
			menuVerticalLayout.setSpacing(true);

			menuVerticalLayout.addComponent(getLogoWrapperHorizontalLayout());
			menuVerticalLayout.addComponent(getOverviewButton());
			menuVerticalLayout.addComponent(getCepButton());
			menuVerticalLayout.addComponent(getSimpleEventsButton());
	        menuVerticalLayout.addComponent(getUsersButton());
	        menuVerticalLayout.addComponent(getSignOutButton());
	        menuVerticalLayout.addComponent(getUserLabel());
		}
		return menuVerticalLayout;
	}
	
	public HorizontalLayout getLogoWrapperHorizontalLayout() {
		if(logoWrapperHorizontalLayout == null) {
			Label logoIcon = new Label("<h1>"+FontAwesome.CIRCLE_O_NOTCH.getHtml()+"</h1>", ContentMode.HTML);
			Label logoText = new Label("<h2><strong>CEP</strong></h2>", ContentMode.HTML);
			Label labelSpace = new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML);
			logoWrapperHorizontalLayout = new HorizontalLayout(logoIcon, labelSpace, logoText);
			logoWrapperHorizontalLayout.setComponentAlignment(logoIcon, Alignment.MIDDLE_CENTER);
			logoWrapperHorizontalLayout.setComponentAlignment(labelSpace, Alignment.MIDDLE_CENTER);
			logoWrapperHorizontalLayout.setComponentAlignment(logoText, Alignment.MIDDLE_CENTER);
			logoWrapperHorizontalLayout.setPrimaryStyleName(ValoTheme.MENU_TITLE);
		}
		return logoWrapperHorizontalLayout;
	}
	
	public Button getOverviewButton() {
		if(overviewButton == null) {
			overviewButton = new Button();
			overviewButton.setIcon(FontAwesome.HOME);
			overviewButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
			overviewButton.setCaption("Overview");
			overviewButton.setSizeUndefined();
			overviewButton.setWidth(BUTTON_WIDTH);
		}
		return overviewButton;
	}
	
	public Button getCepButton() {
		if(cepButton == null) {
			cepButton = new Button();
			cepButton.setIcon(FontAwesome.FILTER);
			cepButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
			cepButton.setCaption("Complex Events");
			cepButton.setSizeUndefined();
			cepButton.setWidth(BUTTON_WIDTH);
		}
		return cepButton;
	}
	
	public Button getSimpleEventsButton() {
		if(simpleEventsButton == null) {
			simpleEventsButton = new Button();
			simpleEventsButton.setIcon(FontAwesome.GEARS);
			simpleEventsButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
			simpleEventsButton.setCaption("Simple Events");
			simpleEventsButton.setSizeUndefined();
			simpleEventsButton.setWidth(BUTTON_WIDTH);
		}
		return simpleEventsButton;
	}
	
	public Button getUsersButton() {
		if(usersButton == null) {
			usersButton = new Button();
			usersButton.setIcon(FontAwesome.USERS);
			usersButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
			usersButton.setCaption("Users");
			usersButton.setSizeUndefined();
			usersButton.setWidth(BUTTON_WIDTH);
		}
		return usersButton;
	}
	
	public Button getSignOutButton() {
		if(signOutButton == null) {
			signOutButton = new Button();
			signOutButton.setIcon(FontAwesome.SIGN_OUT);
			signOutButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
			signOutButton.setCaption("Sing Out");
			signOutButton.setSizeUndefined();
			signOutButton.setWidth(BUTTON_WIDTH);
		}
		return signOutButton;
	}
	
	public Label getUserLabel() {
		if(userLabel == null) {
			userLabel = new Label("User", ContentMode.HTML);
			userLabel.setPrimaryStyleName(ValoTheme.MENU_SUBTITLE);
			userLabel.addStyleName(ValoTheme.LABEL_H4);
			userLabel.setSizeUndefined();
		}
		return userLabel;
	}
}

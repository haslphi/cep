package at.jku.ce.cep.ui.event.simple;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import at.jku.ce.cep.common.ValidatorFactory;
import at.jku.ce.cep.enums.StreamType;

@SuppressWarnings("serial")
public class SimpleEventForm extends CustomComponent {

	private FormLayout mainLayout = null;
	private VerticalLayout actions = null;
	private HorizontalLayout buttonHorizontalLayout = null;
    private Button saveButton = null;
    private Button cancelButton = null;
//    private Button testConnectionButton = null;
//    private Button historyButton = null;
    private TextField name = null;
    private ComboBox type = null;
    private TextField wlanSSID = null;
    private TextField gpsLatitude = null;
    private TextField gpsLongitude = null;
    private TextField timeHour = null;
    private TextField timeMinute = null;
    private TextField timeSpan = null;
    private TextArea description = null;
    
    public SimpleEventForm() {  	
    	super();
    	init();
    }
    
	private void init(){
		this.setCompositionRoot(getMainLayout());
	}
	
	private FormLayout getMainLayout(){
		if (mainLayout == null){
			mainLayout = new FormLayout();
			mainLayout.setMargin(true);
			
			mainLayout.addComponent(getActions());
		}				
		return mainLayout;
	}
	
	private VerticalLayout getActions(){
		if(actions == null) {
			actions = new VerticalLayout();
			actions.setSpacing(true);			
			actions.addComponent(getButtonHorizontalLayout());
			actions.addComponent(getName());
			actions.addComponent(getWlanSSID());
			actions.addComponent(getType());
			actions.addComponent(getGpsLatitude());
			actions.addComponent(getGpsLongitude());
			actions.addComponent(getTimeHour());
			actions.addComponent(getTimeMinute());
			actions.addComponent(getTimeSpan());
			actions.addComponent(getCepEventDescription());
//			actions.addComponent(new HorizontalLayout(getHistoryButton(), getTestConnectionButton()));
		}
		return actions;
	}
	
	private HorizontalLayout getButtonHorizontalLayout(){
		if(buttonHorizontalLayout == null) {
			buttonHorizontalLayout = new HorizontalLayout();
			buttonHorizontalLayout.setSpacing(true);
			buttonHorizontalLayout.addComponent(getSaveButton());
			buttonHorizontalLayout.addComponent(getCancelButton());
		}
		return buttonHorizontalLayout;
	}
	
	public Button getSaveButton(){
		if(saveButton == null) {
			saveButton = new Button("Save");
			saveButton.setIcon(FontAwesome.FLOPPY_O);
			saveButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		}
		return saveButton;
	}	
	
	public Button getCancelButton(){
		if(cancelButton == null) {
			cancelButton = new Button("Cancel");
			cancelButton.setIcon(FontAwesome.BAN);
			cancelButton.addStyleName(ValoTheme.BUTTON_DANGER);
		}
		return cancelButton;
	}
	
//	public Button getTestConnectionButton() {
//		if(testConnectionButton == null) {
//			testConnectionButton = new Button("Test Connection");
//			testConnectionButton.setIcon(FontAwesome.EXCHANGE);
//			testConnectionButton.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
//		}
//		return testConnectionButton;
//	}
	
//	public Button getHistoryButton() {
//		if(historyButton == null) {
//			historyButton = new Button("History");
//			historyButton.setIcon(FontAwesome.HISTORY);
//			historyButton.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
//		}
//		return historyButton;
//	}
	
	public TextField getName(){
		if(name == null) {
			name = new TextField("Name");
			name.setNullRepresentation("");
			name.addValidator(ValidatorFactory.createStringLenghtValidator(name.getCaption(), 1, 200, false));
		}
		return name;
	}
	
	public TextField getWlanSSID(){
		if(wlanSSID == null) {
			wlanSSID = new TextField("WLAN SSID");
			wlanSSID.setNullRepresentation("");
			wlanSSID.addValidator(ValidatorFactory.createStringLenghtValidator(wlanSSID.getCaption(), 1, 200, false));
		}
		return wlanSSID;
	}
	
	public ComboBox getType() {
		if(type == null) {
			type = new ComboBox("Type");
			type.addStyleName("vertical");
			type.setInputPrompt("Event type");
			type.setRequired(true);
			type.setContainerDataSource(new BeanItemContainer<>(
                StreamType.class, StreamType.valuesAsList()));
		}
		return type;
	}
	
	public TextField getGpsLatitude(){
		if(gpsLatitude == null) {
			gpsLatitude = new TextField("Latitude");
			gpsLatitude.setIcon(FontAwesome.MAP_PIN);
			gpsLatitude.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			gpsLatitude.setNullRepresentation("");
			gpsLatitude.addValidator(ValidatorFactory.createDoubleRangeValidator(gpsLatitude.getCaption(), -90, 90));
		}
		return gpsLatitude;
	}
	
	public TextField getGpsLongitude(){
		if(gpsLongitude == null) {
			gpsLongitude = new TextField("Longitude");
			gpsLongitude.setIcon(FontAwesome.MAP_PIN);
			gpsLongitude.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			gpsLongitude.setNullRepresentation("");
			gpsLongitude.addValidator(ValidatorFactory.createDoubleRangeValidator(gpsLongitude.getCaption(), -180, 180));
		}
		return gpsLongitude;
	}
	
	public TextField getTimeHour(){
		if(timeHour == null) {
			timeHour = new TextField("Hour");
			timeHour.setIcon(FontAwesome.CLOCK_O);
			timeHour.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			timeHour.setNullRepresentation("");
			timeHour.addValidator(ValidatorFactory.createIntegerRangeValidator(timeHour.getCaption(), 0, 24));
		}
		return timeHour;
	}
	
	public TextField getTimeMinute(){
		if(timeMinute == null) {
			timeMinute = new TextField("Minute");
			timeMinute.setIcon(FontAwesome.CLOCK_O);
			timeMinute.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			timeMinute.setNullRepresentation("");
			timeMinute.addValidator(ValidatorFactory.createIntegerRangeValidator(timeMinute.getCaption(), 0, 60));
		}
		return timeMinute;
	}
	
	public TextField getTimeSpan(){
		if(timeSpan == null) {
			timeSpan = new TextField("Span");
			timeSpan.setIcon(FontAwesome.CLOCK_O);
			timeSpan.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			timeSpan.setNullRepresentation("");
			timeSpan.addValidator(ValidatorFactory.createIntegerRangeValidator(timeMinute.getCaption(), 0, 60));
		}
		return timeMinute;
	}
	
	public TextArea getCepEventDescription(){
		if(description == null) {
			description = new TextArea("Description");
			description.setIcon(FontAwesome.FILE_TEXT);
			description.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			description.setHeight("100%");
			description.setWidth("100%");
			description.setNullRepresentation("");
			description.addValidator(ValidatorFactory.createStringLenghtValidator(description.getCaption(), 0, 4000, true));
		}
		return description;
	}
	
}

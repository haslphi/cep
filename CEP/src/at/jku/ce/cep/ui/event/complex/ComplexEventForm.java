package at.jku.ce.cep.ui.event.complex;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import at.jku.ce.cep.common.ValidatorFactory;

@SuppressWarnings("serial")
public class ComplexEventForm extends CustomComponent {

	private FormLayout mainLayout = null;
	private VerticalLayout actions = null;
	private HorizontalLayout buttonHorizontalLayout = null;
    private Button saveButton = null;
    private Button cancelButton = null;
//    private Button testConnectionButton = null;
//    private Button historyButton = null;
    private TextField name = null;
    private ComboBox sink = null;
    private CheckBox isActive = null;
    private TextArea description = null;
    private TwinColSelect sources = null;
    
    public ComplexEventForm() {  	
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
			actions.addComponent(getIsActive());
			actions.addComponent(getSink());
			actions.addComponent(getSources());
			actions.addComponent(getCepEventDescription());
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
	
	public TextField getName(){
		if(name == null) {
			name = new TextField("Name");
			name.setNullRepresentation("");
			name.addValidator(ValidatorFactory.createStringLenghtValidator(name.getCaption(), 1, 200, false));
		}
		return name;
	}
	
	public ComboBox getSink() {
		if (sink == null) {
			sink = new ComboBox("Sink");
			sink.addStyleName("vertical");
			sink.setRequired(true);
			sink.setItemCaptionPropertyId("name");
			sink.setInputPrompt("Choose sink");
		}
		return sink;
	}
	
	public CheckBox getIsActive() {
		if(isActive == null) {
			isActive = new CheckBox("Is Active");
		}
		return isActive;
	}
	
	public TextArea getCepEventDescription() {
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
	
	public TwinColSelect getSources() {
		if(sources == null) {
			sources = new TwinColSelect("Select Sources");
			sources.setRows(6);
			sources.setItemCaptionPropertyId("name");
			sources.setRequired(true);
		}
		return sources;
	}
	
}

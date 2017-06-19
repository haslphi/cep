package at.jku.ce.cep.ui.event.simple;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import at.jku.ce.cep.beans.SimpleEvent;

@SuppressWarnings("serial")
public class SimpleEventView extends CustomComponent {

	private HorizontalLayout mainLayout = null;
	private HorizontalLayout actions = null;
	private VerticalLayout left = null;
	private TextField filter = null;
	private Grid beanList = null;
	private Button newBean = null;
	private Button refreshButton = null;
    private SimpleEventForm beanForm = null;
	
	public SimpleEventView(){
		super();
		init();
	}
	
	private void init(){
		this.setCompositionRoot(getMainLayout());
		this.setSizeFull();
	}
	
	public HorizontalLayout getMainLayout(){
		if (mainLayout == null){
			mainLayout = new HorizontalLayout();
			mainLayout.setSizeFull();
			
			mainLayout.addComponent(getLeft());
			mainLayout.addComponent(getBeanForm());
	        mainLayout.setExpandRatio(left, 2f);
	        mainLayout.setExpandRatio(beanForm, 1);
		}				
		return mainLayout;
	}
	
	public VerticalLayout getLeft(){
		if(left == null){
	        left = new VerticalLayout();
	        left.setSizeFull();
	        
	        left.addComponent(getActions());
	        left.addComponent(getBeanList());
	        
	        left.setExpandRatio(beanList, 1);
		}
		return left;
	}
	
	private HorizontalLayout getActions(){
		if(actions == null) {
			actions = new HorizontalLayout();
	        actions.setWidth("100%");
	        
	        actions.addComponent(getFilter());
	        actions.addComponent(getRefreshButton());
	        actions.addComponent(getNewBeanButton());	        
	        
	        actions.setExpandRatio(filter, 1);	        
		}
		return actions;
	}
	
	public TextField getFilter(){
		if(filter == null) {
			filter = new TextField();
	        filter.setWidth("100%");
	        filter.setInputPrompt("Find Event...");
		}
		return filter;
	}
	
	public Button getNewBeanButton(){
		if(newBean == null) {
			newBean = new Button("New Event");
			newBean.setIcon(FontAwesome.PLUS);
		}
		return newBean;
	}
	
	public Button getRefreshButton() {
		if(refreshButton == null) {
			refreshButton = new Button();
			refreshButton.setIcon(FontAwesome.REFRESH);
		}
		return refreshButton;
	}
	
	public SimpleEventForm getBeanForm(){
		if(beanForm == null) {
			beanForm = new SimpleEventForm();
		}
		return beanForm;
	}
	
	public Grid getBeanList(){
		if(beanList == null) {
			beanList = new Grid();
			beanList.setSizeFull();
			beanList.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
			
	        beanList.setContainerDataSource(new BeanItemContainer<>(SimpleEvent.class));
	        beanList.removeAllColumns();
	        beanList.addColumn("name");
	        beanList.addColumn("type");
	        beanList.setColumnOrder("name", "type");
	        
	        beanList.setSelectionMode(Grid.SelectionMode.SINGLE);
		}
		return beanList;
	}
	
}

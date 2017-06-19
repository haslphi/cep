package at.jku.ce.cep.ui.event.complex;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import at.jku.ce.cep.beans.ComplexEvent;

@SuppressWarnings("serial")
public class ComplexEventView extends CustomComponent {

	private HorizontalLayout mainLayout = null;
	private HorizontalLayout actions = null;
	private VerticalLayout left = null;
	private TextField filter = null;
	private Grid beanList = null;
	private Button newBean = null;
	private Button refreshButton = null;
    private ComplexEventForm beanForm = null;
	
	public ComplexEventView(){
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
	        filter.setInputPrompt("Find Complex Event...");
		}
		return filter;
	}
	
	public Button getNewBeanButton(){
		if(newBean == null) {
			newBean = new Button("New Complex Event");
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
	
	public ComplexEventForm getBeanForm(){
		if(beanForm == null) {
			beanForm = new ComplexEventForm();
		}
		return beanForm;
	}
	
	public Grid getBeanList(){
		if(beanList == null) {
			beanList = new Grid();
			beanList.setSizeFull();
			beanList.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
			
	        beanList.setContainerDataSource(new BeanItemContainer<>(ComplexEvent.class));
	        beanList.removeAllColumns();
	        beanList.addColumn("name");
	        beanList.addColumn("sink");
	        beanList.setColumnOrder("name", "sink");
	        
	        beanList.setSelectionMode(Grid.SelectionMode.SINGLE);
		}
		return beanList;
	}
	
}

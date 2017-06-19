package at.jku.ce.cep.ui.event.complex;

import java.util.List;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.SelectionEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;

import at.jku.ce.cep.beans.ComplexEvent;
import at.jku.ce.cep.beans.SimpleEvent;
import at.jku.ce.cep.beans.Sink;
import at.jku.ce.cep.common.NotificationPusher;
import at.jku.ce.cep.test.TestProvider;

public class ComplexEventController {
	
	private ComplexEventView view = null;

    private ComplexEvent cepEvent;
    private BeanFieldGroup<ComplexEvent> formFieldBindings;
	
	public ComplexEventController(){		
		view = new ComplexEventView();
		refreshBeans();
		init();
	}
	
	private void init(){
		view.getFilter().addTextChangeListener(this::filterListener);
		view.getRefreshButton().addClickListener(this::refreshButtonListener);
		view.getNewBeanButton().addClickListener(this::newBeanListener);
		view.getBeanList().addSelectionListener(this::selectionListener);
		view.getBeanForm().getSaveButton().addClickListener(this::saveListener);
		view.getBeanForm().getCancelButton().addClickListener(this::cancelListener);
		
		refreshSinks(false);
		refreshSources();
		
	}
	
	private void filterListener(TextChangeEvent e) {
		refreshBeans(e.getText());
	}
	
	private void refreshButtonListener(ClickEvent e) {
		refreshBeans();
	}
	
	private void newBeanListener(ClickEvent e) {
		edit(new ComplexEvent());
	}
	
	private void selectionListener(SelectionEvent e) {
		edit((ComplexEvent) view.getBeanList().getSelectedRow()); 
	}
	
    public void saveListener(ClickEvent e) {
        try {
        	formFieldBindings.commit();
        	//DaoServiceRegistry.getRobotDAO().save(cepEvent);
        	TestProvider.save(cepEvent);
        	refreshBeans();

        } catch (FieldGroup.CommitException exception) {
            // Validation exceptions could be shown here
        	NotificationPusher.showValidationViolatedNotification(Page.getCurrent());
        }
    }
	
    public void cancelListener(ClickEvent e) {
    	view.getBeanList().select(null);
    	view.getBeanForm().setVisible(false);
    }
    
	
	public ComplexEventView getView(){
		return view;
	}
	
	/**
	 * Refresh the grid without any.
	 * 
	 * @param filter
	 */
	public void refreshBeans() {
        refreshBeans(view.getFilter().getValue());
    }
	
	/**
	 * Refresh the grid with the given filter.
	 * 
	 * @param filter
	 */
    public void refreshBeans(String filter) {
//    	List<SimpleEvent> beans = DaoServiceRegistry.getRobotDAO().findByCriteria(createFilterCriteria(filter));
    	List<ComplexEvent> beans = TestProvider.findCepByFilter(filter);
    	view.getBeanList().setContainerDataSource(new BeanItemContainer<>(
    			ComplexEvent.class, beans));
    	view.getBeanForm().setVisible(false);
    }
    
//    /**
//     * Create a factory for all current visible columns and the give filter string.
//     * 
//     * @param filter 
//     * @return
//     */
//    private CriteriaFactory createFilterCriteria(String filter) {
//    	CriteriaFactory factory = CriteriaFactory.create();
//    	if(StringUtils.isNotBlank(filter)) {
//    		// exclude hidden columns and the port column from searching
//    		view.getBeanList().getColumns().stream().forEach(c -> {
//    			if(!c.isHidden() && !"port".equalsIgnoreCase(c.getPropertyId().toString())) {
//    				factory.orLike(c.getPropertyId().toString(), filter);
//    			}
//    		});
//    	}
//    	return factory;
//    }
    
    /**
	 * Refresh the drawing beans in the path combobox.
	 */
	private void refreshSinks(boolean keepSelected) {
		Object selected = view.getBeanForm().getSink().getValue();
		List<Sink> beans = TestProvider.findSinkByFilter(null);
    	view.getBeanForm().getSink().setContainerDataSource(new BeanItemContainer<>(
                Sink.class, beans));
    	if(keepSelected) {
    		view.getBeanForm().getSink().select(selected);	
    	}
	}
	
	private void refreshSources() {
		List<SimpleEvent> beans = TestProvider.findByFilter(null);
    	view.getBeanForm().getSources().setContainerDataSource(new BeanItemContainer<>(
    			SimpleEvent.class, beans));
	}
     
    /**
     * Set the user form visible.<br>
     * Set the user to the form.<br>
     * Make the current selected user editable.
     * 
     * @param complexEvent
     */
    public void edit(ComplexEvent complexEvent) {
        this.cepEvent = complexEvent;
        
        if(complexEvent != null) {
            // Bind the properties of the bean POJO to fields in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(complexEvent, view.getBeanForm());
            view.getBeanForm().getName().focus();
        }
        view.getBeanForm().setVisible(complexEvent != null);
    }
    
    public BeanFieldGroup<ComplexEvent> getFormFieldBindings(){
    	return formFieldBindings;
    }
 
}

package at.jku.ce.cep.ui.overview;

import java.util.List;

import com.google.common.eventbus.Subscribe;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;
import com.vaadin.ui.renderers.ImageRenderer;

import at.jku.ce.cep.CepUI;
import at.jku.ce.cep.beans.ComplexEvent;
import at.jku.ce.cep.beans.ComplexEvent.ComplexEventPersistenceEvent;
import at.jku.ce.cep.beans.Note;
import at.jku.ce.cep.test.TestProvider;

public class OverviewController {
	
	private OverviewView view = null;
//	DCharts chart = null;
	private Note userNote = null;
	
	public OverviewController(){
		view = new OverviewView();
		init();
	}
	
	private void init() {
		// get the user notes
		userNote = new at.jku.ce.cep.beans.Note();
		userNote.setNotes(
				"Remember to:\n· Zoom in and out in the Sales view\n· Filter the transactions and drag a set of them to the Reports tab\n· Create a new report\n· Change the schedule of the movie theater");
		if(userNote != null && userNote.getNotes() != null) {
			view.getNotesTextArea().setValue(userNote.getNotes());
		}
		
		// configure notes save menu item
		if(view.getNoteComponent().getTools() != null) {
			// add listener to save item
			view.getNoteComponent().getTools().getItems().get(1).setCommand(this::notesSaveSelectedListener);
		}
		
		// fill drawing grid and add listeners
		refreshDrawingGrid();
    	ImageRenderer renderer = (ImageRenderer) view.getDrawingGrid().getColumn("icon").getRenderer();
    	renderer.addClickListener(this::drawingIconClickEvent);
    	view.getDrawingGrid().addItemClickListener(this::drawingItemClickListener);
		
		// create the chart component and fill it with data
//		Component c = createChartSlot();
//		refreshDrawingChart();
		
		//view.getDashboardPanelsCssLayout().addComponent(c);
		view.getDashboardPanelsCssLayout().addComponent(view.getDrawingComponent());
		
		// register to the eventbus
		CepUI.getEventBus().register(this);
	}
	
	private void notesSaveSelectedListener(MenuItem selectedItem) {
		saveNote();
		Notification notif = new Notification("Save done");
		notif.setPosition(Position.TOP_CENTER);
		notif.show(Page.getCurrent());
	}
	
	private void drawingItemClickListener(ItemClickEvent event) {
		if(event.isDoubleClick() && event.getItemId() != null) {
			//CepUI.getEventBus().post(new OpenDrawingEvent((Drawing) event.getItemId()));
		}
	}
	
	private void drawingIconClickEvent(RendererClickEvent event) {
//		Drawing bean = (Drawing)event.getItemId();
//		if(bean != null && !bean.isNew()) {
//			GripUI.getEventBus().post(new OpenDrawingEvent(bean));
//		}
	}
	
	/**
	 * Listen to {@link DrawingPersistenceEvent}s of {@link Drawing} beans.
	 * 
	 * @param event
	 */
	@Subscribe
	public void listenDrawingPersistenceAction(ComplexEventPersistenceEvent event) {
		refreshDrawingGrid();
//		refreshDrawingChart();
	}
	
	public OverviewView getView(){
		return view;
	}
	
	/**
	 * Save the user notes.
	 */
	public void saveNote() {
//		userNote.setNotes(view.getNotesTextArea().getValue());
//		DaoServiceRegistry.getNoteDAO().save(userNote);
	}
	
	/**
	 * Refresh the data in the drawing grid.
	 */
	private void refreshDrawingGrid() {
		List<ComplexEvent> beans = TestProvider.findCepByFilter(null);
    	view.getDrawingGrid().setContainerDataSource(new BeanItemContainer<>(
    			ComplexEvent.class, beans));
	}
	
}

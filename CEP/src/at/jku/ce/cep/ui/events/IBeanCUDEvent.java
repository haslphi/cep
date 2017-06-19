package at.jku.ce.cep.ui.events;

import at.jku.ce.cep.beans.GenericEntity;
import at.jku.ce.cep.beans.GenericPK;
import at.jku.ce.cep.enums.UpdateType;

/**
 * Create/Update/Delete Event for persistence Beans
 * 
 * @author Philipp
 *
 */
public interface IBeanCUDEvent {
	
	public GenericEntity<? extends GenericPK> getBean();
	public UpdateType getUpdateType();

}

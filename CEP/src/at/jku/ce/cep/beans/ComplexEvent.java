package at.jku.ce.cep.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.vaadin.server.ThemeResource;

import at.jku.ce.cep.enums.UpdateType;
import at.jku.ce.cep.ui.events.IBeanCUDEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "COMPLEX_EVENT", uniqueConstraints={@UniqueConstraint(columnNames = {ComplexEvent.COLUMN_NAME, HistoryPK.COLUMN_HISTORY})})
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "description")
public class ComplexEvent extends HistorizableEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5321846261943219726L;
	
	private static final ThemeResource ICON = new ThemeResource("img/ic_open_in_new_24px.png");
	
	public static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_IS_ACTIVE = "IS_ACTIVE";
	private static final String COLUMN_SINK = "SINK";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	
	@Column(name = COLUMN_NAME, length = 200, nullable = false)
	private String name;
	
	@Column(name = COLUMN_IS_ACTIVE, nullable = false)
	private Boolean isActive = false;
	
	private Set<SimpleEvent> sources;
	
	@Column(name = COLUMN_SINK, nullable = false)
	private Sink sink;
	
	@Column(name = COLUMN_DESCRIPTION, length = 4000)
	private String description;
	
	@Transient
	ThemeResource icon = ICON;
	
	@Override
	public IBeanCUDEvent createEvent(UpdateType updateType) {
		return new ComplexEventPersistenceEvent(this, updateType);
	}
	
	/**
	 * Event that can be sent when a {@link ComplexEvent} bean is created/updated/deleted.
	 */
	@AllArgsConstructor
	public static class ComplexEventPersistenceEvent implements IBeanCUDEvent {
		private ComplexEvent bean = null;
		private UpdateType updateType = null;

		@Override
		public GenericEntity<? extends GenericPK> getBean() {
			return bean;
		}

		@Override
		public UpdateType getUpdateType() {
			return updateType;
		}
		
	}

}

package at.jku.ce.cep.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import at.jku.ce.cep.enums.StreamType;
import at.jku.ce.cep.enums.UpdateType;
import at.jku.ce.cep.ui.events.IBeanCUDEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SIMPLE_EVENT", uniqueConstraints={@UniqueConstraint(columnNames = {SimpleEvent.COLUMN_NAME, HistoryPK.COLUMN_HISTORY})})
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "description")
public class SimpleEvent extends HistorizableEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5321846261943219726L;
	
	public static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_TYPE = "TYPE";
	private static final String COLUMN_WLAN_SSID = "WLAN_SSID";
	private static final String COLUMN_GPS_LATITUDE = "GPS_LATITUDE";
	private static final String COLUMN_GPS_LONGITUDE = "GPS_LONGITUDE";
	private static final String COLUMN_TIME_HOUR = "TIME_HOUR";
	private static final String COLUMN_TIME_MINUTE = "TIME_MINUTE";
	private static final String COLUMN_TIME_SPAN = "TIME_SPAN";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	
	@Column(name = COLUMN_NAME, length = 200, nullable = false)
	private String name;
	
	@Column(name = COLUMN_TYPE)
	@Enumerated(EnumType.STRING)
	private StreamType type;
	
	@Column(name = COLUMN_WLAN_SSID, length = 200)
	private String wlanSSID;
	
	@Column(name = COLUMN_GPS_LATITUDE)
	private Double gpsLatitude;
	
	@Column(name = COLUMN_GPS_LONGITUDE)
	private Double gpsLongitude;
	
	@Column(name = COLUMN_TIME_HOUR)
	private Integer timeHour;
	
	@Column(name = COLUMN_TIME_MINUTE)
	private Integer timeMinute;
	
	@Column(name = COLUMN_TIME_SPAN)
	private Integer timeSpan;
	
	@Column(name = COLUMN_DESCRIPTION, length = 4000)
	private String description;
	
//	public void copyAttributesFlat(CepEvent bean) {
//		name = bean.getName();
//		host = bean.getHost();
//		port = bean.getPort();
//		description = bean.getDescription();
//	}
	
	@Override
	public IBeanCUDEvent createEvent(UpdateType updateType) {
		return new SimpleEventPersistenceEvent(this, updateType);
	}
	
	/**
	 * Event that can be sent when a {@link SimpleEvent} bean is created/updated/deleted.
	 */
	@AllArgsConstructor
	public static class SimpleEventPersistenceEvent implements IBeanCUDEvent {
		private SimpleEvent bean = null;
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

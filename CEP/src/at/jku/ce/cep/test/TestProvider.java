package at.jku.ce.cep.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.eventbus.EventBus;

import at.jku.ce.cep.CepUI;
import at.jku.ce.cep.beans.ComplexEvent;
import at.jku.ce.cep.beans.GenericEntity;
import at.jku.ce.cep.beans.GenericPK;
import at.jku.ce.cep.beans.HistoryPK;
import at.jku.ce.cep.beans.SimpleEvent;
import at.jku.ce.cep.beans.Sink;
import at.jku.ce.cep.beans.User;
import at.jku.ce.cep.enums.StreamType;
import at.jku.ce.cep.enums.UpdateType;
import at.jku.ce.cep.ui.events.IBeanCUDEvent;

/**
 * Provide workarounds for testing and presentation
 * 
 * @author Philipp
 *
 */
public class TestProvider {
	public static Map<HistoryPK, SimpleEvent> simpleEventMap = new HashMap<>();
	public static Map<HistoryPK, ComplexEvent> complexEventMap = new HashMap<>();
	public static Map<HistoryPK, Sink> sinkMap = new HashMap<>();
	
	static {
		// SIMPLE EVENTS
		SimpleEvent homeWlan = new SimpleEvent();
		homeWlan.setName("Home Wlan");
		homeWlan.setType(StreamType.WLAN_CONNECT);
		homeWlan.setWlanSSID("MyHomeWlan");
		homeWlan.preCreate();
		homeWlan.setVersion(0);
		simpleEventMap.put(homeWlan.getId(), homeWlan);
		
		SimpleEvent arrivingTime = new SimpleEvent();
		arrivingTime.setName("Arriving Time");
		arrivingTime.setType(StreamType.TIME);
		arrivingTime.setTimeHour(18);
		arrivingTime.setTimeMinute(30);
		arrivingTime.setTimeSpan(20);
		arrivingTime.preCreate();
		arrivingTime.setVersion(0);
		simpleEventMap.put(arrivingTime.getId(), arrivingTime);
		
		SimpleEvent homeLocation = new SimpleEvent();
		homeLocation.setName("Home Location");
		homeLocation.setType(StreamType.GPS);
//		homeLocation.setGpsLatitude(48.171227);
//		homeLocation.setGpsLongitude(14.036159);
		homeLocation.setGpsLatitude(48.171);
		homeLocation.setGpsLongitude(14.036);
		homeLocation.preCreate();
		homeLocation.setVersion(0);
		simpleEventMap.put(homeLocation.getId(), homeLocation);
		
		SimpleEvent smallSpan = new SimpleEvent();
		smallSpan = new SimpleEvent();
		smallSpan.setName("Small Event Span");
		smallSpan.setType(StreamType.TIMESPAN);
		smallSpan.setTimeSpan(1);
		smallSpan.preCreate();
		smallSpan.setVersion(0);
		simpleEventMap.put(smallSpan.getId(), smallSpan);
		
		// SINKS
		Sink  sink = new Sink();
		
		Sink startSpot = new Sink();
		startSpot.setName("Start Spotify");
		startSpot.preCreate();
		startSpot.setVersion(0);
		sinkMap.put(startSpot.getId(), startSpot);
		
		sink = new Sink();
		sink.setName("Stop Spotify");
		sink.preCreate();
		sink.setVersion(0);
		sinkMap.put(sink.getId(), sink);
		
		sink = new Sink();
		sink.setName("Drive up Table");
		sink.preCreate();
		sink.setVersion(0);
		sinkMap.put(sink.getId(), sink);
		
		sink = new Sink();
		sink.setName("Drive down Table");
		sink.preCreate();
		sink.setVersion(0);
		sinkMap.put(sink.getId(), sink);
		
		// COMPLEX EVENTS
		Set<SimpleEvent> sources = new HashSet<>();
		sources.add(homeWlan);
		sources.add(arrivingTime);
		sources.add(homeLocation);
		sources.add(smallSpan);
		ComplexEvent complexEvent = new ComplexEvent();
		complexEvent.setName("Post work music");
		complexEvent.setSources(sources);
		complexEvent.setSink(startSpot);
		complexEvent.setIsActive(true);
		complexEvent.preCreate();
		complexEvent.setVersion(0);
		complexEventMap.put(complexEvent.getId(), complexEvent);
	}

	public static User provideUser(String username, String password) {
		User user = new User();
		user.setFirstName("Rocket");
		user.setLastName("Raccoon");
		user.setUsername("demo");
		user.setPassword("demo");
		user.preCreate();
		user.setVersion(0);
		
		if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	public static SimpleEvent save(SimpleEvent event) {
		if(event != null) {
			UpdateType updateType;
			if(event.isNew()) {
				updateType = UpdateType.ADD;
				event.preCreate();
				event.setVersion(0);
			} else {
				updateType = UpdateType.UPDATE;
				event.setVersion(event.getVersion()+1);
			}
			simpleEventMap.put(event.getId(), event);
			sendCUDEvent(event, updateType);
		}
		return event;
	}
	
	public static ComplexEvent save(ComplexEvent event) {
		if(event != null) {
			UpdateType updateType;
			if(event.isNew()) {
				updateType = UpdateType.ADD;
				event.preCreate();
				event.setVersion(0);
			} else {
				updateType = UpdateType.UPDATE;
				event.setVersion(event.getVersion()+1);
			}
			complexEventMap.put(event.getId(), event);
			sendCUDEvent(event, updateType);
		}
		return event;
	}
	
	public static List<SimpleEvent> findByFilter(String filter) {
		List<SimpleEvent> eventList = new ArrayList<>();
		boolean isNotBlank = StringUtils.isNotBlank(filter);
		for (SimpleEvent event : simpleEventMap.values()) {
			if(!isNotBlank || StringUtils.containsIgnoreCase(event.getName(), filter) || 
					StringUtils.contains(event.getType().toString(), filter)) {
				eventList.add(event);
			}
		}
		return eventList;
	}
	
	public static List<ComplexEvent> findCepByFilter(String filter) {
		List<ComplexEvent> eventList = new ArrayList<>();
		boolean isNotBlank = StringUtils.isNotBlank(filter);
		for (ComplexEvent event : complexEventMap.values()) {
			if(!isNotBlank || StringUtils.containsIgnoreCase(event.getName(), filter)) {
				eventList.add(event);
			}
		}
		return eventList;
	}
	
	public static List<Sink> findSinkByFilter(String filter) {
		List<Sink> beanList = new ArrayList<>();
		boolean isNotBlank = StringUtils.isNotBlank(filter);
		for (Sink sink : sinkMap.values()) {
			if(!isNotBlank || StringUtils.containsIgnoreCase(sink.getName(), filter)) {
				beanList.add(sink);
			}
		}
		return beanList;
	}
	
	/**
	 * Send an {@link IBeanCUDEvent} over the {@link EventBus}
	 * 
	 * @param bean
	 * @param updateType
	 */
	protected static <T extends GenericEntity<? extends GenericPK>> void sendCUDEvent(T bean, UpdateType updateType) {
		IBeanCUDEvent event = bean.createEvent(UpdateType.UPDATE);
		if(event != null) {
			CepUI.getEventBus().post(event);
		}
	}
	
}

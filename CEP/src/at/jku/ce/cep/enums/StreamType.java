package at.jku.ce.cep.enums;

import java.util.Arrays;
import java.util.List;

public enum StreamType {
	GPS,
	WLAN_CONNECT,
	WLAN_DISCONNECT,
	TIME,
	TIMESPAN;
	
	public static List<StreamType> valuesAsList() {
		// this kind of list is immutable
		return Arrays.asList(StreamType.values());
	}
}

package at.jku.ce.cep.ui.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import at.jku.ce.cep.beans.User;

@Getter
@AllArgsConstructor
public class LoginEvent {
	private boolean validLogin = false;
	private User user = null;
}
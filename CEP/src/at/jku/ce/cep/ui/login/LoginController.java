package at.jku.ce.cep.ui.login;

import java.security.GeneralSecurityException;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;

import at.jku.ce.cep.CepUI;
import at.jku.ce.cep.beans.User;
import at.jku.ce.cep.common.Constants;
import at.jku.ce.cep.common.NotificationPusher;
import at.jku.ce.cep.dao.DaoServiceRegistry;
import at.jku.ce.cep.security.CryptoService;
import at.jku.ce.cep.test.TestProvider;
import at.jku.ce.cep.ui.events.LoginEvent;

public class LoginController {

	private LoginView view = null;

	public LoginController() {
		view = new LoginView();
		init();
	}

	private void init() {
		view.getSignInButton().addClickListener(this::signIn);
	}

	private void signIn(Button.ClickEvent event) {
		String username = view.getUsernameTextField().getValue();
		String password = view.getPasswordPasswordField().getValue();
		User user = null;
		
		if(StringUtils.isNoneBlank(username, password)) {
			try {
//				user = DaoServiceRegistry
//						.getUserDAO()
//						.findByUsernameAndPassword(username, CryptoService.getInstance().encrypt(password, Constants.CRYPTO_KEY));
				user = TestProvider.provideUser(username, CryptoService.getInstance().encrypt(password, Constants.CRYPTO_KEY));
			} catch (GeneralSecurityException e) {
				NotificationPusher.showError(Page.getCurrent(), new Exception(), null);
				e.printStackTrace();
			}
		}
		if(user != null && !user.isNew() && !user.isDeleted()) {	
			CepUI.getEventBus().post(new LoginEvent(true, user));	
		} else {
			showLoginDeclinedNotification();
			CepUI.getEventBus().post(new LoginEvent(false, null));			
		}
//		CepUI.getEventBus().post(new LoginEvent(true, user));	
		
	}

	public LoginView getView() {
		return view;
	}
	
	private void showLoginDeclinedNotification() {
		String caption = "User does not exist or username/password is wrong!";
		NotificationPusher.showCustomError(Page.getCurrent(), null, caption, null);
	}
}

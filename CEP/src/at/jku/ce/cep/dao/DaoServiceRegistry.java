package at.jku.ce.cep.dao;

import java.util.UUID;

public class DaoServiceRegistry {
//	private static IUserDAO userDAO;
	
	/**
	 * Generate a uuid for the (composite) pk
	 * 
	 * @return 32 char long alpha-numeric string
	 */
	public static String provideUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
//	/**
//	 * @return instance of the {@link IUserDAO}
//	 */
//	public static IUserDAO getUserDAO(){
//		if(userDAO == null) {
//			userDAO = new UserDAO();
//		}
//
//		return userDAO;
//	}
	
}

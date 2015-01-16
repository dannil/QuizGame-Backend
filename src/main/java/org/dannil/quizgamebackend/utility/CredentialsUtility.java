package org.dannil.quizgamebackend.utility;

import java.util.LinkedList;
import java.util.List;

/**
 * Class which performs operations on credentials, such as login.
 * 
 * @author Daniel Nilsson
 */
public class CredentialsUtility {

	private static List<String> logins;

	static {
		logins = new LinkedList<String>();
		logins.add("Basic YXBpLXRlc3Q6MmZiNWUxMzQxOWZjODkyNDY4NjVlN2EzMjRmNDc2ZWM2MjRlODc0MA==");
	}

	public CredentialsUtility() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Verify if the given login exists.
	 * 
	 * @param username
	 * 					the username to check
	 * @param token
	 * 					the token to check
	 * 
	 * @return true if the login exists, otherwise false
	 */
	public static final boolean isAuthCorrect(final String httpBasicAuthToken) {
		if (httpBasicAuthToken == null) {
			return false;
		}
		return logins.contains(httpBasicAuthToken);
	}
}

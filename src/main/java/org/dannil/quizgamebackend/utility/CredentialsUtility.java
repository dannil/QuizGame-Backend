package org.dannil.quizgamebackend.utility;

public class CredentialsUtility {

	public static final String USERNAME = "api-test";
	public static final String TOKEN = "2fb5e13419fc89246865e7a324f476ec624e8740";

	public CredentialsUtility() {
		throw new UnsupportedOperationException();
	}

	public static final boolean isLoginCorrect(final String username, final String token) {
		return (username.equals(CredentialsUtility.USERNAME) || token.equals(CredentialsUtility.TOKEN));
	}

}

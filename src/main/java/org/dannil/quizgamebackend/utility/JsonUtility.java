package org.dannil.quizgamebackend.utility;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtility {

	public final static String generateJson(final Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

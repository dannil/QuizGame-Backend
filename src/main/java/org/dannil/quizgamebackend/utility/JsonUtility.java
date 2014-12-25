package org.dannil.quizgamebackend.utility;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtility {

	public final static String convertToStandard(final String json) {
		return json.toLowerCase().replace("null", "[]");
	}

	public final static String generateJson(final Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}

}

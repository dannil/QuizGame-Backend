package org.dannil.quizgamebackend.utility;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.dannil.quizgamebackend.model.Question;

public final class JsonUtility {

	/**
	 * Convert the specified object to a JSON string.
	 * 
	 * @param obj
	 * 				the object to convert
	 * 
	 * @return a JSON representation of the specified object
	 * 
	 * @throws IOException
	 * 						if the object couldn't be parsed to JSON
	 */
	public final static <T> String convertToJson(final T obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}

	/**
	 * Convert the specified JSON string to an object.
	 * 
	 * @param json
	 * 				the JSON to convert
	 * 
	 * @return a object representation of the specified JSON string
	 * 
	 * @throws IOException
	 * 						if the JSON string couldn't be parsed to a object
	 */
	public final static Question convertFromJson(final String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Question.class);
	}

}

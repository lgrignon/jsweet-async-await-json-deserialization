package org.jsweet.examples.deserialization;

import static def.dom.Globals.console;
import static def.dom.Globals.document;
import static def.es6.Globals.fetch;

import java.util.List;

import org.jsweet.examples.deserialization.model.Profile;
import org.jsweet.examples.deserialization.model.User;

import def.dom.HTMLElement;
import def.es6.Globals.FetchResponse;

public class ModelDeserializationExample {

	private final static String MODEL_JSON_URL = "model.json";

	private HTMLElement htmlOutput;

	public ModelDeserializationExample() {
		htmlOutput = (HTMLElement) document.querySelector("#htmlOutput");

		fetch(MODEL_JSON_URL) //
				.thenOnfulfilledFunction((FetchResponse response) -> {
					if (!response.ok) {
						console.log("cannot fetch model from JSON file: " + MODEL_JSON_URL);
						htmlOutput.style.color = "rgb(200, 100, 100)";
						htmlOutput.innerHTML = "cannot fetch model from JSON file: " + MODEL_JSON_URL;
					} else {
						console.log("model loaded from JSON file: " + MODEL_JSON_URL);
						htmlOutput.style.color = "black";
						htmlOutput.innerHTML = "model loaded from JSON file: " + MODEL_JSON_URL;

						response.text() //
								.thenOnfulfilledFunction((String modelJson) -> {

									onJsonModelAvailable(modelJson);

									return null;
								}) //
								.catchOnrejectedFunction(this::onError);
					}

					return null;
				}) //
				.catchOnrejectedFunction(this::onError);
	}

	private void onJsonModelAvailable(String modelListJson) {
		console.log("JSON model: " + modelListJson);

		JsonModelDeserializer deserializer = new JsonModelDeserializer();
		List<User> users = deserializer.decode(modelListJson);

		console.log("users deserialized");
		console.log(users);

		htmlOutput.style.color = "rgb(100, 200, 100)";
		htmlOutput.innerHTML += "<br />users: " + users;

		for (int i = 0; i < users.size(); i++) {
			htmlOutput.innerHTML += "<br />user #" + (i + 1) + " is User?: " + (users.get(i) instanceof User);
			htmlOutput.innerHTML += "<br />user #" + (i + 1) + "'s profile is Profile?: "
					+ (users.get(i).profile instanceof Profile);
		}
	}

	private Object onError(Object error) {
		htmlOutput.style.color = "rgb(200, 100, 100)";
		htmlOutput.innerHTML = "An error occurred: " + error;

		return null;
	}

	public static void main(String... args) {
		new ModelDeserializationExample();
	}
}

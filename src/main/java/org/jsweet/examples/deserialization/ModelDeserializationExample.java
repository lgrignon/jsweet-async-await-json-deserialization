package org.jsweet.examples.deserialization;

import static def.dom.Globals.console;
import static def.dom.Globals.document;
import static def.dom.Globals.setTimeout;
import static def.es6.Globals.fetch;
import static jsweet.util.Lang.async;
import static jsweet.util.Lang.asyncReturn;
import static jsweet.util.Lang.await;
import static jsweet.util.Lang.function;

import java.util.List;
import java.util.function.Consumer;

import org.jsweet.examples.deserialization.model.Profile;
import org.jsweet.examples.deserialization.model.User;

import def.dom.HTMLElement;
import def.es6.Globals.FetchResponse;
import def.js.Promise;
import jsweet.lang.Async;

public class ModelDeserializationExample {

	private final static String MODEL_JSON_URL = "model.json";

	private HTMLElement htmlOutput;

	public ModelDeserializationExample() {
		htmlOutput = (HTMLElement) document.querySelector("#htmlOutput");

		loadModelJson();

		showDelayedMessage();
	}

	@Async
	private void loadModelJson() {
		try {
			FetchResponse response = await(fetch(MODEL_JSON_URL)); //
			if (!response.ok) {
				console.log("cannot fetch model from JSON file: " + MODEL_JSON_URL);
				htmlOutput.style.color = "rgb(200, 100, 100)";
				htmlOutput.innerHTML = "cannot fetch model from JSON file: " + MODEL_JSON_URL;
			} else {
				console.log("model loaded from JSON file: " + MODEL_JSON_URL);
				htmlOutput.style.color = "black";
				htmlOutput.innerHTML = "model loaded from JSON file: " + MODEL_JSON_URL;

				String modelJson = await(response.text()); //
				onJsonModelAvailable(modelJson);
			}
		} catch (Error e) {
			this.onError(e);
		}
	}

	private void onJsonModelAvailable(String modelListJson) {
		console.log("JSON model: " + modelListJson);

		JsonModelDeserializer deserializer = new JsonModelDeserializer();
		List<User> users = deserializer.decode(modelListJson);

		console.log("users deserialized");
		console.log(users);

		htmlOutput.style.color = "rgb(100, 200, 100)";
		htmlOutput.innerHTML += "<br />";

		String userListHtml = " <ul>";

		for (int i = 0; i < users.size(); i++) {
			userListHtml += "<li>user #" + (i + 1) + " is User?: " + (users.get(i) instanceof User) + " </li>";
			userListHtml += "<li>user #" + (i + 1) + "'s profile is Profile?: "
					+ (users.get(i).profile instanceof Profile) + " </li>";

		}
		userListHtml += "</ul>";
		htmlOutput.innerHTML += userListHtml;

	}

	private Object onError(Object error) {
		htmlOutput.style.color = "rgb(200, 100, 100)";
		htmlOutput.innerHTML = "An error occurred: " + error;

		return null;
	}

	private void showDelayedMessage() {
		def.js.Function getResultAsync = async(function(() -> {
			await(delay(3000));

			htmlOutput.innerHTML += "<br />printed after async/await delay!!";

			return asyncReturn(42);
		}));

		Promise<Integer> resultPromise = getResultAsync.$apply();
		resultPromise //
				.thenAsync(result -> {

					htmlOutput.innerHTML += "<br />> you have long waited to know that the answer is: " + result;
					return getAnotherAnswer("What is the age of the captain");
				}) //
				.then((String otherResult) -> {

					htmlOutput.innerHTML += "<br />> " + otherResult;
				});
	}

	@Async
	private Promise<String> getAnotherAnswer(String baseQuestion) {
		await(delay(2000));

		return asyncReturn("but I can tell you more: \"" + baseQuestion + "\" is not a good question");
	}

	private Promise<Void> delay(int millis) {
		return new Promise<Void>((Consumer<Void> resolve, Consumer<Object> reject) -> {
			setTimeout(resolve, millis);
		});
	}

	public static void main(String... args) {
		new ModelDeserializationExample();
	}
}

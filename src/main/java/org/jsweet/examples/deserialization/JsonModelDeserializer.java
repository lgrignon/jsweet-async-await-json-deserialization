package org.jsweet.examples.deserialization;

import static jsweet.util.Lang.object;
import static def.dom.Globals.window;

import java.util.List;

import org.jsweet.examples.deserialization.model.User;

import def.js.JSON;
import def.js.Object;

interface Newable {
	Object $new();
}

public class JsonModelDeserializer {

	private final String modelPackage;

	public JsonModelDeserializer() {
		String modelClassName = User.class.getName();
		modelPackage = modelClassName.substring(0, modelClassName.lastIndexOf("."));
		System.out.println("model package: " + modelPackage);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> decode(String jsonList) {
		return (List<T>) JSON.parse(jsonList, (key, value) -> {
			Object valueObject = object(value);
			if (valueObject != null && valueObject.hasOwnProperty("__type")) {
				String type = (String) valueObject.$get("__type");
				String className = modelPackage + "." + type;

				Newable constructor = resolveConstructor(className);

				Object typedValue = Object.assign(constructor.$new(), valueObject);

				System.out.println("turned value into: (type=" + type + ")");
				System.out.println(typedValue);

				return typedValue;
			} else {
				return valueObject;
			}
		});
	}

	private Newable resolveConstructor(String className) {
		Object constructor = object(window);
		for (String part : className.split(".")) {
			constructor = constructor.$get(part);
		}

		return (Newable) constructor;
	}

}

package org.jsweet.examples.deserialization.model;

public class Profile {

	// this field is used to 'revive' the objects when deserialized
	public String __type = Profile.class.getSimpleName();

	public String name;
}

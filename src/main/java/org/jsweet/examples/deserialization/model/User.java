package org.jsweet.examples.deserialization.model;

public class User {

	// this field is used to 'revive' the objects when deserialized
	public String __type = User.class.getSimpleName();

	public String name;
	public boolean active;
	public Integer age;
	
	public Profile profile;
}

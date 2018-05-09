package def.es6;

import def.js.Promise;
import jsweet.lang.Interface;

public class Globals {
	@Interface
	public static abstract class FetchResponse {
		public boolean ok;
		
		public native Promise<String> text();
	}
	public native static Promise<FetchResponse> fetch(String path);
}

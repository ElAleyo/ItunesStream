package acv2server.apps.IS.test;

import java.io.FileNotFoundException;

import acv2server.apps.IS.main.LibraryObject;

public class Tester {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//LibraryObject l = new LibraryObject("C:\\Users\\jose\\Music\\iTunes\\iTunes Music Library.xml");
		//LibraryObject l = new LibraryObject("C:\\Users\\jose\\Desktop\\t.txt");
		LibraryObject l = new LibraryObject("/Users/jose/Music/iTunes/iTunes Music Library.xml");
	}

}

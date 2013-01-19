package acv2server.apps.IS.test;

import java.io.FileNotFoundException;

import acv2server.apps.IS.main.ItunesLibrary;

public class Tester {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//LibraryObject l = new LibraryObject("C:\\Users\\jose\\Music\\iTunes\\iTunes Music Library.xml");
		//LibraryObject l = new LibraryObject("C:\\Users\\jose\\Desktop\\t.txt");
		ItunesLibrary l = new ItunesLibrary("/Users/joseacevedo/Desktop/iTunes Library.xml");
		
		for(String str : l.getSongList())
			System.out.println(str);
		
		//for (String str : l.print())
		//	System.out.println(str);
		

	}

}

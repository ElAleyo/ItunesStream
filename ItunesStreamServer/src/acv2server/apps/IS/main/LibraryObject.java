package acv2server.apps.IS.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class provides methods for accessing the Itunes Library file (C:\localhost\Music\Itunes\"Itunes Music Library.xml") 
 * @author Jose
 *
 */
public class LibraryObject {

	private Map<Integer, LibraryItem> imlItems;
	private File file; 
	/**
	 * 
	 * @param filename The location of the Itunes Music Library xml file
	 * @throws FileNotFoundException 
	 */
	public LibraryObject(String filename) throws FileNotFoundException
	{		
		//Initialize fields		
		this.file = new File(filename);
		this.imlItems = new HashMap<Integer, LibraryItem>();
		
		//Check if file exists
		if(file.exists())
		{
			
		}
		else
		{
			throw new FileNotFoundException("File "+filename+" was not found");
		}
	}
}
	
	
	
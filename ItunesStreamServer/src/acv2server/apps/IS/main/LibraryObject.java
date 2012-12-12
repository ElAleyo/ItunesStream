package acv2server.apps.IS.main;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
			this.parseFile();
		}
		
		else
			throw new FileNotFoundException("File "+filename+" was not found");
		
	}
	/**
	 * This methods takes the file and populates the Map 
	 * @throws FileNotFoundException 
	 */
	private void parseFile() throws FileNotFoundException {
	
		Scanner sc = new Scanner(file );
		//Pattern p = Pattern.compile("<key>[0-9]+</key>\\s<dict>*</dict>");
		sc.useDelimiter("<key>[0-9]+</key>");
		LibraryItem li;
		
		while(sc.hasNext())
		{
			li = new LibraryItem(sc.next());
			this.imlItems.put(li.getKey(), li);
		}
		sc.close();
	}
}
	
	
	
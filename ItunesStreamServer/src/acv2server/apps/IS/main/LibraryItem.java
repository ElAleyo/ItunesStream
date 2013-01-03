package acv2server.apps.IS.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * This class represents a Song in the library.
 * The data we use for now is:
 * -The name 
 * -The location 
 * -The key
 * - 
 * @author jose
 *
 */
public class LibraryItem {

	private String location;
	private Integer key;
	private String name;
	
	private Pattern p;
	private Matcher m;

	/**
	 * In this constructor we parse through the string given as parameter 
	 * and extract the fields needed for this class  
	 * @param item the item as it appears in the Itunes Music Libaray.xml file
	 */
	public LibraryItem(String item)
	{
		if(this.isValid(item))
		{
			this.extractKey(item);
			this.extractLocation(item);
			this.extractName(item);
		}
		else
		{
			this.location = name = null;
			this.key = null;
		}
	}

	/**
	 * Returns the key that iTunes assigns to its songs
	 * @return the key assigned by Itunes. If the value is null then this item is not a 
	 * valid one
	 */
	public Integer getKey()
	{
		return this.key;
	}
	/**
	 * Returns the location of the song on the hard drive
	 * @return the location of the song on the hard drive  If the value is null then this item is not a 
	 * valid one
	 */
	public String getLocation()
	{
		return this.location;
	}
	/**
	 * Returns the name of the song 
	 * @return the name of the song  If the value is null then this item is not a 
	 * valid one
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * This methods extracts the Key for this item. If it can't extract the Key null is
	 * assigned to Key 
	 */
	private void extractKey(String item)
	{
		//TODO
		p = Pattern.compile("<key>Track ID</key><integer>[0-9]+</integer>");
	    m = p.matcher(item);
	    m.find();
	    String temp = m.group();
	    temp = temp.substring(28, temp.indexOf("</string"));
	    this.name = temp;
	}

	/**
	 * This method extracts the Location of this item. If it can't extract the Location null is
	 * assigned to location 
	 */
	private void extractLocation(String item)
	{
		p = Pattern.compile("<key>Location</key><string>[a-zA-Z].+</string>");
	    m = p.matcher(item);
	    String temp;
	    if(m.find())
	    {	
	    	temp = m.group();
	    	temp = temp.substring(27, temp.indexOf("</string"));
	    }
	    //If no match for location is found then the track is found online 
	    else
	    	temp = "ONLINE";
	    this.name = temp;
	}

	/**
	 * This method extracts the Name of this item. If it can't extract the name null is
	 * assigned to name
	 */
	private void extractName(String item)
	{
		p = Pattern.compile("<key>Name</key><string>[a-zA-Z].+</string>");
	    m = p.matcher(item);
	    m.find();
	    String temp = m.group();
	    temp = temp.substring(23, temp.indexOf("</string"));
	    this.name = temp;
	}

	/**
	 * Verifies that the item given as parameter to the constructor is structured in a correct way.
	 * @param item the item as it appears in the Itunes Music Libaray.xml file
	 * @return true if it's valid, false if it's not.
	 */
	private boolean isValid(String item)
	{
		p = Pattern.compile("<key>Track ID</key><integer>[0-9]+</integer>");
		m = p.matcher(item);
		return m.find();
	}
}


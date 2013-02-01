package acv2server.apps.IS.main;

import java.net.URI;
import java.net.URISyntaxException;
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
	private boolean isSong;

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
			this.extractName(item);
			this.extractLocation(item);
			this.checkIfSong(item);
		}
		else
		{
			this.location = name = null;
			this.key = null;
		}
	}

	/**
	 * Returns the key that iTunes assigns to its songs
	 * @return the key assigned by iTunes. If the value is null then this item is not a 
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
	 * Checks whether this item is a song or other type. 
	 * @return true if this item is a song
	 */
	public boolean isSong()
	{
		return this.isSong;
	}

	/**
	 * This methods extracts the Key for this item. If it can't extract the Key null is
	 * assigned to Key 
	 */
	private void extractKey(String item)
	{
		p = Pattern.compile("<key>Track ID</key><integer>([0-9]+)</integer>");
		m = p.matcher(item);
		m.find(); // This is not surrounded by an if because every song should have a key
		String temp = m.group(1);

		this.key = Integer.valueOf(temp);
	}

	/**
	 * This method extracts the Location of this item. If it can't extract the Location null is
	 * assigned to location 
	 */
	private void extractLocation(String item)
	{
		p = Pattern.compile("<key>Location</key><string>(.*?)</string>");
		m = p.matcher(item);
		String temp;
		if(m.find())
			temp = m.group(1);
		
		//If no match for location is found then the track is found online or Remote as they type it.
		//p = Pattern.compile("<key>Track Type</key><string>Remote</string>");
		else
			temp = "ONLINE";
		try {
			URI test = new URI(temp);
			this.location = test.getPath();
		} catch (URISyntaxException e) {
			System.out.println("Error on: "+this.name );
			this.location = temp;
			e.printStackTrace();
		}
	}

	/**
	 * This method extracts the Name of this item. If it can't extract the name null is
	 * assigned to name
	 */
	private void extractName(String item)
	{
		p = Pattern.compile("<key>Name</key><string>(.*?)</string>", Pattern.DOTALL);
		m = p.matcher(item);
		String temp = "no name";
		if(m.find())
			temp = m.group(1);

		this.name = temp;
	}

	/**
	 * Checks if this item has a valid Kind (different audio or video formats)
	 * @param item the item as it appears in the iTunes Music Libaray.xml file
	 */
	private void checkIfSong(String item)
	{
		p = Pattern.compile("<key>Kind</key><string>(.*?)</string>");
		m = p.matcher(item);
		String temp="";
		if(m.find())
			temp = m.group(1);

		if(this.validSongKind(temp))
			this.isSong = true;
		else
			this.isSong = false;
	}

	/**
	 * Verifies that the item given as parameter to the constructor is structured in a correct way.
	 * @param item the item as it appears in the iTunes Music Libaray.xml file
	 * @return true if it's valid, false if it's not.
	 */
	private boolean isValid(String item)
	{
		p = Pattern.compile("<key>Track ID</key><integer>[0-9]+</integer>");
		m = p.matcher(item);
		return m.find();
	}

	/**
	 * This method checks for the <key>Kind</key>. If it contains a valid music format
	 * then it returns true.
	 * @return true if this item is a valid song, false otherwise 
	 */
	private boolean validSongKind(String songKind)
	{
		if(songKind.equals("MPEG audio file"))
			return true;
		else if(songKind.equals("Purchased AAC audio file"))
			return true;

		else{
			//System.out.println("Unkwon Type: -"+songKind+"- for item: "+ this.name);
			return false;
		}
	}
}


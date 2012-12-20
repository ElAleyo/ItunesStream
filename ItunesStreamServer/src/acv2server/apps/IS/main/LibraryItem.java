package acv2server.apps.IS.main;

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

	/**
	 * In this constructor we parse through the string given as parameter 
	 * and extract the fields needed for this class  
	 * @param item the item as it appears in the Itunes Music Libaray.xml file
	 */
	public LibraryItem(String item)
	{
		if(this.isValid(item))
		{
			this.extractKey();
			this.extractLocation();
			this.extractName();
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
	private void extractKey()
	{
		//TODO
	}

	/**
	 * This method extracts the Location of this item. If it can't extract the Location null is
	 * assigned to location 
	 */
	private void extractLocation()
	{
		//TODO
	}

	/**
	 * This method extracts the Name of this item. If it can't extract the name null is
	 * assigned to name
	 */
	private void extractName()
	{
		//TODO
	}

	/**
	 * Verifies that the item given as parameter to the constructor is structured in a correct way.
	 * @param item the item as it appears in the Itunes Music Libaray.xml file
	 * @return true if it's valid, false if it's not.
	 */
	private boolean isValid(String item)
	{
		//TODO
		return true;
	}
}


package acv2server.apps.IS.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import acv2server.apps.IS.main.ItunesLibrary;

public class Tester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//LibraryObject l = new LibraryObject("C:\\Users\\jose\\Music\\iTunes\\iTunes Music Library.xml");
		//LibraryObject l = new LibraryObject("C:\\Users\\jose\\Desktop\\t.txt");
		ItunesLibrary l = new ItunesLibrary("/Users/joseacevedo/Desktop/iTunes Library.xml");
		
		//for(String str : l.getSongList())
		//	System.out.println(str);
		
		
		BufferedWriter bw = writeFileOut("/Users/joseacevedo/Desktop/output.txt");
		
		for (String str : l.print())
		{
			//System.out.println(str);
			bw.write(str+"\n");
		}
		
		bw.close();
		
	}
	
	
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static BufferedWriter writeFileOut(String filename) throws IOException
	{
		File f = new File(filename);
		if (!f.exists()) {
			f.createNewFile();
		}
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		return bw;
	}

}

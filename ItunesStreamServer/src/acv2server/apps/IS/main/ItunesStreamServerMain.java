package acv2server.apps.IS.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public class ItunesStreamServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		if(args.length == 1 )
		{
			try {

				String location = args[0];
				
				ItunesLibrary il = new ItunesLibrary(location);

				TCPServer tcp = new TCPServer(il);
				System.out.println("server");
				tcp.start();
				tcp.close();	
				System.out.println("close");
				

			} catch (FileNotFoundException e) {
				System.out.println("File not Found");
				e.printStackTrace();
			} catch (UnknownHostException e) {
				System.out.println("Unkown Host");	
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO Error");
				e.printStackTrace();
			}
		}
		else
		{

		}

	}
}

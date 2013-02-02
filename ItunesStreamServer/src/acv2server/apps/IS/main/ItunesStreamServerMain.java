package acv2server.apps.IS.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import acv2server.apps.IS.online.TCPServer;

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

				TCPServer tcp = new TCPServer(il, 8888);
				
				System.out.println("Server Started");
				tcp.start();
				tcp.close();	
				System.out.println("Server Closed");
				

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

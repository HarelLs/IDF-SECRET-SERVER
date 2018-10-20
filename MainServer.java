package in.SERVERCLIENT;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class MainServer extends Thread{
	public static String outios = "";
	public static int workerscon = 0;
	private ArrayList<SERVER> workerslist = new ArrayList<>();
	private int serverport;
	public MainServer(int serverport) {
		this.serverport = serverport;
	}
	@Override
	public void run() {
		System.out.println("Waiting for Client Conection...");
		try {
			ServerSocket serversocket = new ServerSocket(serverport);
			while(true) {
				Socket clientsocket  = serversocket.accept();
				System.out.println(clientsocket.getInetAddress());
				SERVER thread = new SERVER(this ,clientsocket);
				workerslist.add(thread);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	public List<SERVER> getworkerslist(){
		return workerslist;
	}
}

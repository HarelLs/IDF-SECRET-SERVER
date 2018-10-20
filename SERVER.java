package in.SERVERCLIENT;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;



public class SERVER extends Thread{
	private final MainServer server;
	private Socket socket;
	OutputStream out;
	static String Login;
	int che = 0;
	static Scanner in = new Scanner(System.in);
	
	public SERVER(MainServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}
	@Override
	
	public void run() {
		Soorver();
	}
	
	public void Soorver() {


		System.out.println("BOOTING...");
		try {
			String line;
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			while((line = reader.readLine()) != null) {
				String[] cmd = line.split(" ");
				
				if(cmd[0].equalsIgnoreCase("dir")) {
					out.write((dir() + "\n").getBytes());
					System.out.println("Sending Dir");
				}
				
				if(cmd[0].equalsIgnoreCase("accept")) {
					String name = cmd[1];
					stringtofile(cmd[2],name);
					addDir(name);
					System.out.println("File Has Been Written.");
				}
				
				if(cmd[0].equalsIgnoreCase("get")) {
					String mid = filetoString(cmd[1]);
					mid += "==" + packetcheck(mid);
					out.write(("write " + mid + " \n").getBytes());
					System.out.println("The File Was Sent.");
				}

			}
		} catch (IOException e) {

			}

	}

	public static void stringtofile(String tofile , String filename) {
		String file = "en\\" + filename +".en";
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(tofile.getBytes());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String filetoString(String file) {
		String input = "";
		String filename = "en\\" + file + ".en";
		try {
			int i = 10000000;
			byte[] buffer = new byte[i];
			FileInputStream inputStream = new FileInputStream(filename);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));

			//  int total = 0;
			@SuppressWarnings("unused")
			int nRead = 0;

			i = inputStream.read(buffer);
			byte[] bufferr = new byte[i];
			//System.out.println(i);
			while((nRead = in.read(bufferr)) != -1) {

				//System.out.println(new String(buffer));
				//   total += nRead;
			} 

			input = new String(bufferr); 

			//input = new String(bufferr);
			//System.out.println(input);
		}catch (Exception e) {
			System.err.println(e);
		}
		return input;
	}

	public String getLogin() {
		return Login;
	}
	
	public String dir() {
		String a = "";
		String yaout = "";
		String filename = "en\\files";
		try {
			int i = 10000;
			byte[] buffer = new byte[i];
			FileInputStream inputStream = new FileInputStream(filename);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));

			//  int total = 0;
			@SuppressWarnings("unused")
			int nRead = 0;

			i = inputStream.read(buffer);
			byte[] bufferr = new byte[i];
			//System.out.println(i);
			while((nRead = in.read(bufferr)) != -1) {
				a =  a + new String(bufferr);
			} 
			yaout = a;
			
		}catch (Exception e) {
			System.err.println(e);
		}
		return yaout;
	}
	
	public void addDir(String item) {
		String a = "";
		String filename = "en\\files";
		try {
			int i = 1000;
			byte[] buffer = new byte[i];
			FileInputStream inputStream = new FileInputStream(filename);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));

			//  int total = 0;
			@SuppressWarnings("unused")
			int nRead = 0;

			i = inputStream.read(buffer);
			byte[] bufferr = new byte[i];
			//System.out.println(i);
			while((nRead = in.read(bufferr)) != -1) {
				
			} 

			a = new String(bufferr);
			for (int j = 0; j < a.split(",").length; j++) {
				if(a.split(",")[j].equalsIgnoreCase(item)) {
					return;
				}
			}
				FileOutputStream ou = new FileOutputStream(filename);
				ou.write((a + "," + item).getBytes());
				ou.flush();
				ou.close();
		}catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static String[] spliti(String input, int divide) {
		String[] a = new String[divide + 1];
		int mid = input.length() / divide; //how much of the String chars.
		int man = input.length() % divide;
		for (int i = 0; i < divide; i++) {
			int c = 1 + i;
			a[i] = input.substring((mid * i), (mid * c));
			if(i == (divide - 1)) {
				a[i+1] = input.substring((mid * c), ((mid * c) + man));
			}
		}
		return a;
	}

	public static int packetcheck(String thefile) {
		int nob = 0;
		for (int i = 0; i < thefile.length(); i++) {
			nob++;
		}
		return nob;
	}
	
}

package in.SERVERCLIENT;

public class run {
	public static void main(String[] args) {
		MainServer server = new MainServer(42069);
			server.start();
	}
}

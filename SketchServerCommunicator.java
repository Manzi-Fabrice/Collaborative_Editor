import java.io.*;
import java.net.Socket;

/**
 * Handles communication between the server and one client, for SketchServer
 */
public class SketchServerCommunicator extends Thread {
	private Socket sock;					// to talk with client
	private BufferedReader in;				// from client
	private PrintWriter out;				// to client
	private SketchServer server;			// handling communication for

	public SketchServerCommunicator(Socket sock, SketchServer server) {
		this.sock = sock;
		this.server = server;
	}

	/**
	 * Sends a message to the client
	 * @param msg
	 */
	public void send(String msg) {
		out.println(msg);
	}
	
	/**
	 * Keeps listening for and handling (your code) messages from the client
	 */
	/**
	 * Keeps listening for and handling messages from the client.
	 */
	public void run() {
		try {
			System.out.println("someone connected");

			// Communication channel
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			// Tell the client the current state of the world
			// TODO: YOUR CODE HERE
			send(server.getSketch().toString());

			// Keep getting and handling messages from the client
			// TODO: YOUR CODE HERE
			String message;
			while ((message = in.readLine()) != null) {
				// Handle the message (not implemented here)
				System.out.println("Received message from client: " + message);
			}

			// Clean up -- note that also remove self from server's list so it doesn't broadcast here
			server.removeCommunicator(this);
			out.close();
			in.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

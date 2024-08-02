import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Handles communication to/from the server for the editor
 * This class establishes a connection to a server and sends/receives messages.
 * It also contains methods to decode and handle incoming messages.
 */
public class EditorCommunicator extends Thread {
	private static PrintWriter out;        // to server
	private BufferedReader in;             // from server
	protected Editor editor;               // handling communication for

	/**
	 * Establishes connection and in/out pair
	 * @param serverIP The IP address of the server to connect to
	 * @param editor The editor object associated with this communicator
	 */
	public EditorCommunicator(String serverIP, Editor editor) {
		this.editor = editor;
		System.out.println("connecting to " + serverIP + "...");
		try {
			Socket sock = new Socket(serverIP, 4242);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("...connected");
		}
		catch (IOException e) {
			System.err.println("couldn't connect");
			System.exit(-1);
		}
	}

	/**
	 * Sends message to the server
	 * @param msg The message to be sent
	 */
	public static void send(String msg) {
		out.println(msg);
	}

	/**
	 * Decodes and handles messages from the server
	 * @param msg The message received from the server
	 */
	public void decoder(String msg)
	{
		String[] message = msg.split(":");
		// method format
		// add : ellipse
		if (message.length >= 2) {
			if (message[0].equals("recolor")) {
				ECRecolor(message);
			}

			if (message[0].equals("delete")) {
				ECDelete(message);
			}

			if (message[0].equals("move")) {
				ECMove(message);
			}

			if (message[0].equals("draw")) {
				ECDraw(message);
			}
		}
	}

	/**
	 * Handles recoloring of shapes based on received message
	 * @param msg The message received from the server
	 */
	public void ECRecolor(String[] msg)
	{
		Shape currShape = editor.getSketch().idShapes.get(msg[1]);
		currShape.setColor(Color.decode(msg[2]));
	}

	/**
	 * Handles deletion of shapes based on received message
	 * @param msg The message received from the server
	 */
	public void ECDelete(String[] msg)
	{

		Shape currShape = editor.getSketch().idShapes.get(msg[1]);
		currShape = null;
	}

	/**
	 * Handles drawing of shapes based on received message
	 * @param msg The message received from the server
	 */
	public void ECDraw(String[] msg)
	{
		Shape currShape = editor.getSketch().idShapes.get(msg[1]);
		currShape.draw(editor.getGraphics());
	}

	/**
	 * Handles movement of shapes based on received message
	 * @param msg The message received from the server
	 */
	public void ECMove(String[] msg)
	{
		Shape currShape = editor.getSketch().idShapes.get(msg[1]);
		// TO DO:
	}

	/**
	 * Listens for and handles messages from the server
	 */
	public void run() {

		try {
			// Handle messages
			String inline;
			while ((inline = in.readLine()) != null)
			{

			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally {
			System.out.println("server hung up");
		}
	}
}

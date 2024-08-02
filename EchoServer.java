import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * EchoServer is a simple server which accepts a connection and 
 * simply reads input and echos it back to the sender.
 * Code provided to enable testing of (1) sending/receiving messages from
 * the server, and (2) updating a sketch based on messages.
 */
public class EchoServer {

    private ServerSocket listen;  // ServerSocket for accepting connections

    /**
     * Constructs an EchoServer with the given ServerSocket.
     * @param listen The ServerSocket for accepting connections
     */
    public EchoServer(ServerSocket listen) {
        this.listen = listen;
    }

    /**
     * Inner class representing a communicator thread for handling communication with a client.
     */
    private class EchoServerCommunicator extends Thread {
        private Socket sock;             // Socket for communication with the client
        private BufferedReader in;       // BufferedReader for reading input from client
        private PrintWriter out;         // PrintWriter for writing output to client

        /**
         * Constructs an EchoServerCommunicator with the given Socket.
         * @param sock The Socket for communication with the client
         */
        public EchoServerCommunicator(Socket sock) {
            this.sock = sock;
        }

        /**
         * Run method for the communicator thread.
         * Handles communication with the client: reads input and echoes it back.
         */
        public void run() {
            try {
                System.out.println("Editor connected for testing...");

                // Communication channel
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                out = new PrintWriter(sock.getOutputStream(), true);

                // Echo loop: read input from client and echo it back
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Received: " + line);
                    send(line);  // Echo back to the client
                }

                // Clean up
                out.close();
                in.close();
                sock.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Sends a message to the client.
         * @param msg The message to send
         */
        public void send(String msg) {
            System.out.println("Send: " + msg);
            out.println(msg);
        }
    }

    /**
     * Accepts connections and starts communicator threads for handling each connection.
     * Continuously listens for new connections.
     * @throws IOException if an I/O error occurs when waiting for a connection
     */
    public void getConnections() throws IOException {
        while (true) {
            // Accept a connection and start a new communicator thread to handle it
            EchoServerCommunicator comm = new EchoServerCommunicator(listen.accept());
            comm.setDaemon(true);  // Set the thread as a daemon thread
            comm.start();          // Start the communicator thread
        }
    }

    /**
     * Main method to start the EchoServer.
     * @param args Command line arguments (not used)
     * @throws Exception if an error occurs
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Starting up the EchoServer...");
        new EchoServer(new ServerSocket(4242)).getConnections();
    }
}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * * Java program to create a simple HTTP Server to demonstrate how to use *
 * ServerSocket and Socket class. * * @author Javin Paul
 */
public class SimpleHTTPServer {
	public static void main(String args[]) throws IOException {
		ServerSocket server = new ServerSocket(8080);
		System.out.println("Listening for connection on port 8080 ....");
		while (true) {
			try (Socket socket = server.accept()) {
				Path path=Paths.get("C:\\Users\\deepa\\Desktop\\response.json");
				byte[] fileBytes = Files.readAllBytes(path);
				String data = new String(fileBytes);
				Date today = new Date();
				String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + data;
				socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
			}
		}
	}
}

//Read more: https://javarevisited.blogspot.com/2015/06/how-to-create-http-server-in-java-serversocket-example.html#ixzz5TVZijExa
package task;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket servSocket = new ServerSocket(9090);
        while (true) {
            Socket accept = servSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String request = reader.readLine();
            int counter = 0;
            System.out.println(counter++);

            if (request.contains("GET")) {
//                Socket socket = new Socket("91.232.128.68", 8080);

                String json = "{\n" +
                        "  \"firstName\": \"Pavlo\",\n" +
                        "  \"lastName\": \"Khshanovskyi\",\n" +
                        "  \"city\": \"Ivano-Frankivsk\",\n" +
                        "  \"hobby\": \"\",\n" +
                        "  \"team\": \"Svydovets\",\n" +
                        "  \"role\": \"Participant\",\n" +
                        "  \"roommate\": \"Nikolay Prokopchuk\",\n" +
                        "  \"roommatesFavoriteDish\": \"\"\n" +
                        "}";

                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream(), "UTF8"));
                wr.write("HTTP/1.1 200 OK\r\n");
                wr.write("Next: http://172.16.11.10:8080\r\n");
                wr.write("Content-Length: " + json.length() + "\r\n");
                wr.write("Content-Type: application/json\r\n");
                wr.write("\r\n");
                wr.write(json + "\r\n");
                wr.flush();
            }
        }
    }
}

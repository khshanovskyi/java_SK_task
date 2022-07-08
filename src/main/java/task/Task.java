package task;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private static final String json = "{\n" +
            "  \"firstName\": \"Pavlo\",\n" +
            "  \"lastName\": \"Khshanovskyi\",\n" +
            "  \"city\": \"Ivano-Frankivsk\",\n" +
            "  \"hobby\": \"\",\n" +
            "  \"team\": \"Svydovets\",\n" +
            "  \"role\": \"Participant\",\n" +
            "  \"roommate\": \"Nikolay Prokopchuk\",\n" +
            "  \"roommatesFavoriteDish\": \"\"\n" +
            "}";

    @SneakyThrows
    public static void main(String[] args) {
        try(ServerSocket servSocket = new ServerSocket(9090)){
            AtomicInteger counter = new AtomicInteger(0);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true) {
                Socket accept = servSocket.accept();
                executorService.submit(() -> process(accept));
                counter.incrementAndGet();
                System.out.println("" + counter.get());
            }
        }

    }

    @SneakyThrows
    private static void process(Socket accept){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String request = reader.readLine();
            if (request.contains("GET")) {
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream(), "UTF8"));
                wr.write("HTTP/1.1 200 OK\r\n");
                wr.write("Next: http://172.16.11.10:8080\r\n");
                wr.write("Content-Length: " + json.length() + "\r\n");
                wr.write("Content-Type: application/json\r\n");
                wr.write("\r\n");
                wr.write(json + "\r\n");
                wr.flush();
                wr.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

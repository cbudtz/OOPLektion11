package ioExamples;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            ServerSocket serverSocket = new ServerSocket(11111);
            //noinspection InfiniteLoopStatement
            while(true){
                Socket accept = serverSocket.accept();
                executorService.execute(()->{
                    System.out.println("current socket: " +accept.getPort() );
                    try {
                        Scanner scan = new Scanner(accept.getInputStream());
                        System.out.println(scan.nextLine());
                        OutputStream outputStream = accept.getOutputStream();
                        PrintWriter printWriter = new PrintWriter(outputStream);
                        printWriter.println("HTTP/1.1 200 OK");
                        printWriter.println();
                        printWriter.println("<html><body>");
                        printWriter.println("<h1>Velkommen</h1>");
                        printWriter.println("</body></html>");
                        printWriter.flush();
                        accept.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

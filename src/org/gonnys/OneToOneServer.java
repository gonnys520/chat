package org.gonnys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneToOneServer {

    public static void main(String[] args) {

        System.out.println("SERVER RUN >>>>>>>>>>>");
        try (ServerSocket serverSocket = new ServerSocket(7777);
             // auto closeable만 들어가야한다. 이 밑에는 socket만드는 거 밖에 못함.
             Socket socket = serverSocket.accept(); // Blocking
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream din = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println("대화 요청이 들어왔습니다.");

            new Thread(() -> {
                try {
                    while (true) {
                        //read and then write - Blocking
                        String otherMsg = din.readUTF();
                        System.out.println(otherMsg);
                    }
                } catch (Exception e) {
                }
            }).start();

            while (true) {
                String myMsg = scanner.nextLine();
                dos.writeUTF(myMsg);
                dos.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }//catch close

    }//main close
}//class close

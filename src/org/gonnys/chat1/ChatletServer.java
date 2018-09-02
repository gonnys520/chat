package org.gonnys.chat1;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatletServer {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("Ready............");
        Socket socket = serverSocket.accept();

        Chatlet chatlet = new Chatlet(socket);

        //이 Thread에서 read가 실행된다.
        new Thread(chatlet).start();
        //Thread가 기존 3개에서 2개로 줄어들었다.
        chatlet.write();

        //main은 END고 thread 2개만 남겨진다.
//        new Thread(() ->{
//            try {
//                chatlet.read();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();

//        new Thread(()->{
//            try {
//                chatlet.write();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();

        System.out.println("END");



    }


}

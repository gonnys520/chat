package org.gonnys.chat1;
import java.net.Socket;

public class ChatletClient {

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("10.10.10.85",7777);
        System.out.println("Client ready");
        Chatlet chatlet = new Chatlet(socket);

        new Thread(chatlet).start();
        chatlet.write();


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


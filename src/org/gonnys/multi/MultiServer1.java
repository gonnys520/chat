package org.gonnys.multi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiServer1 {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(7777);

        System.out.println("READY>>>>>>>>>>>>>>>>>>>>>>");
        List<DataOutputStream> dosList = new ArrayList<>();


        //계속 돌면서 accept를 받아야한다.
        while (true) {

            Socket client = serverSocket.accept();
            DataInputStream din = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dosList.add(dos);

            new Thread(() -> {
                while (true) {
                    String msg = null;
                    try {
                        msg = din.readUTF();
                        System.out.println(client.getInetAddress()+ ":" + msg);

                        for(DataOutputStream ds:dosList){
                            ds.writeUTF("SERVER: " + msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(msg);
                }
            }).start();
        }//while close
    }//main close
}//class close

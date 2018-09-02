package org.gonnys.chat1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Chatlet implements Runnable{

    // 모든 시작은 socket으로 시작하기때문에 생성자를 만들어주는 게 좋다
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dos;
    private Scanner scanner;

    public Chatlet(Socket socket) throws Exception {
        this.socket = socket;
        this.din = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.scanner = new Scanner(System.in);
    }

    public void write() throws Exception {
        while (true) {
            String msg = scanner.nextLine();

            //A:Hello -> 전체에게
            //S:92:Hello -> 92번에게 귓속말

            dos.writeUTF(msg);
        }
    }

    public void read() throws Exception {
        while (true) {
            String msg = din.readUTF();
            System.out.println(msg);
        }
    }

    //try,catch의 특징: Exception이 발생하면 다음으로 넘긴다.
    //따라서 위 코드에서 예외가 발생해도 다음 코드들은 닫힐 수 있도록 해야한다.
    public void closeAll(){
        try { scanner.close();}catch (Exception e){}
        try { dos.close();}catch (Exception e){}
        try { din.close();}catch (Exception e){}
        try { socket.close();}catch (Exception e){}

    }


    //이게 진짜 Thread가 관통하는 코드
    @Override
    public void run() {
        //override에 exception을 할 수 없으니까 Surround with try/catch 해야한다.
        //하위클래스에서 재정의하는데 부모가 없이 자식에서 throws exception 할 수 없음
        try {
            read();
        } catch (Exception e) {
            e.printStackTrace();
            closeAll();
        }
    }
}

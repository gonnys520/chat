package org.gonnys;

//객체 1, 쓰레드n개 ver
public class Ex1 implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i <10000; i++){
            System.out.println(Thread.currentThread().getName()+":"+ this);
        }
    }

    public static void main(String[] args) {
        Ex1 obj = new Ex1();

        //괄호안에 Runnable Type도 들어갈 수 있다.
        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
    }
}

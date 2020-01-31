import sun.awt.geom.AreaOp;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main implements Runnable {
    private long countfrom;
    private long countto;


    Main(long countfrom, long countto) {
        this.countfrom = countfrom;
        this.countto = countto;
    }

    long sum = 0;

    @Override
    public void run() {
        synchronized (this) {
            for (long i = countfrom; i < countto; i++) {
                sum += i;
            }
            System.out.println(Thread.currentThread().getName() + " " + sum);
        notify();
        }
    }


}
class A{
    public  static void main(String[] args) throws InterruptedException{

        Main task1 = new Main(0,1000);
        Thread thread1 = new Thread(task1);

        Main task2 = new Main(1000, 2000);
        Thread thread2 = new Thread(task2);
        Main task3 = new Main(2000, 3001);
        Thread thread3 = new Thread(task3);

        thread1.start();
        synchronized(thread1) {
            try {
                System.out.println("Waiting for "+ thread1 + " to complete...");
                thread1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread2.start();
            synchronized(thread2) {
                try {
                    System.out.println("Waiting for "+ thread2 + " to complete...");
                    thread2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        thread3.start();
        synchronized(thread3) {
            try {
                System.out.println("Waiting for "+ thread3 + " to complete...");
                thread3.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//
//        thread1.join();
//        thread2.join();
//        thread3.join();

        System.out.println(task1.sum + task2.sum + task3.sum + "   final " + Thread.currentThread().getName());
//        System.out.println(args[1]);
    }
}

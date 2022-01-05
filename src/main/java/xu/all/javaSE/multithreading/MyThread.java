package xu.all.javaSE.multithreading;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread:" + getName());
    }
}

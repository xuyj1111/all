package xu.all.javaSE.multithread;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread:" + getName());
    }
}

package Timer;

import java.util.Timer;
import java.util.TimerTask;

public class Test {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        }, 1000, 2000);

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + "---");
            }
        }, 1000, 2000);
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + "--222222222222-");
            }
        }, 1000);//只执行一次
        // Thread.sleep(5000);
        // timer.cancel();
    }
}

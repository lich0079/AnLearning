package statePattern;
public class Person {
    public static final int WORK = 0;
    public static final int PLAY = 1;
    public static final int SLEEP = 2;
    public static final int FUN = 3;

    public int state;

    public void WorkToPlay() {
        state = 1;
        System.out.println("work to play");
    }

    public void PlayToSleep() {
        state = 2;
        System.out.println("play to sleep");
    }

    public void SleepToFun() {
        state = 3;
        System.out.println("sleep to fun");
    }

    public void FunToWork() {
        state = 0;
        System.out.println("fun to work");
    }

    public void setState(int end) {
        if (end > FUN || end < WORK) {
            System.out.println("end is wrong state");
            return;
        }
        if (end - 1 > state) {
            setState(end - 1);
        } else if (end - 1 == state || state == end + 3) {
            // end在递归中不断减小 但在这里就不会继续递归减小了 因为这里什么也没做 所以下面的else if没机会执行
            // 除非是初始判断
        } else if (end == state) {
            System.out.println("do nothing");
            return;
        } else if (end < state) {
            // System.out.println("<");
            setState(end - 1 >= 0 ? end - 1 : end + 3);
        }
        // if (state == end - 1 || state==end+3) {
        System.out.println(state + " to " + end);
        switch (state) {
        case WORK:
            WorkToPlay();
            break;
        case PLAY:
            PlayToSleep();
            break;
        case SLEEP:
            SleepToFun();
            break;
        case FUN:
            FunToWork();
            break;
        default:
            break;
        }
        // }
    }

    public static void main(String[] args) {
        Person a = new Person();
        a.state = 2;
        a.setState(1);
        System.out.println("---");
        a.setState(0);
        System.out.println("---");
        a.setState(3);
    }
}

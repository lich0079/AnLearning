package statePattern;

public class SleepState implements State {

    public void play(User user) {
        this.work(user);
        user.Play();
    }

    public void sleep(User user) {
        System.out.println("sleep");
        user.setState(new WorkState());
    }

    public void work(User user) {
        user.Sleep();
        user.Work();
    }

}

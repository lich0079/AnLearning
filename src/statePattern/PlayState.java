package statePattern;

public class PlayState implements State {

    public void play(User user) {
        System.out.println("play");
        user.setState(new SleepState());
    }

    public void sleep(User user) {
        user.Play();
        user.Sleep();
    }

    public void work(User user) {
        user.Play();
        user.Sleep();
        user.Work();
    }

}

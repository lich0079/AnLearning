package statePattern;

public class WorkState implements State {

    public void play(User user) {
        user.Work();
        user.Play();
    }

    public void sleep(User user) {
        this.play(user);
        user.Sleep();
    }

    public void work(User user) {
        System.out.println("work");
        user.setState(new PlayState());
    }
}

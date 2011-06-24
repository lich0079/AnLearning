package statePattern;

public interface State {
    void work(User user);
    void play(User user);
    void sleep(User user);
}

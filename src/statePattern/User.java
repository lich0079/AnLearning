package statePattern;
public class User {
    public static final int WORK = 0;
    public static final int PLAY = 1;
    public static final int SLEEP = 2;

    public int stateCode;
    private State state=new WorkState();

    public void Work() {
        state.work(this);
    }

    public void Play() {
        state.play(this);
    }

    public void Sleep() {
        state.sleep(this);
    }


    public static void main(String[] args) {
        User a = new User();
        a.Sleep();
        System.out.println("-----------");
        a.Play();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}

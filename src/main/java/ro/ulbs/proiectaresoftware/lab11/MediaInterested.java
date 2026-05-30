package ro.ulbs.proiectaresoftware.lab11;


interface   Observer {
    void update(String message);
}

public class MediaInterested implements Observer {
    private String name;

    public MediaInterested(String name) {
        this.name = name;
    }
@Override

public void update(String message) {

        doSomeLogic(message);

}
    public void doSomeLogic(String message) {
        System.out.println("["+name + "] received update: " + message);
    }

}
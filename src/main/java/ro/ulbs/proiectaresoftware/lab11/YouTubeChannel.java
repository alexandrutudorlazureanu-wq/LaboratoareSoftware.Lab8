package ro.ulbs.proiectaresoftware.lab11;

import java.util.ArrayList;
import java.util.List;


interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String message);
}


public  class YouTubeChannel implements Subject {
    private String channelName;
    private List<Observer> observers = new ArrayList<>();

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }


    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }


    public void uploadVideo(String title) {
        System.out.println("{"+ channelName + "} uploaded a new video: " + title);
    }
}
package design.observer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Teacher {
    private String name;

    private String className;

    List<Observer<Teacher>> observers = new ArrayList<>();


    public Teacher addObserver(Observer<Teacher> observer) {
        observers.add(observer);
        return this;
    }

    public void publishEvent(Event<Teacher> event) {
        for (Observer<Teacher> observer : observers) {
            observer.eventListener(event);
        }
    }
}

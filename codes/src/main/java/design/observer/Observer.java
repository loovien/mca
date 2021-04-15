package design.observer;

public interface Observer<T> {
    void eventListener(Event<T> event);
}

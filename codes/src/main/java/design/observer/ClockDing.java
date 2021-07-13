package design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * created 7/13/2021 5:51 PM
 *
 * @author luowen <loovien@163.com>
 */
public class ClockDing {

    public static class Clock extends Observable {

        public void lesionOff() {
            System.out.println("lesion end");
            this.setChanged();
            this.notifyObservers("lesion end");
        }
    }

    public static class Student implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            System.out.println(o.countObservers());
            System.out.println(o.hasChanged());
            System.out.println("bye bye: " + arg);
        }
    }

    public static class Teacher implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("close book");
        }
    }

    public static void main(String[] args) {
        Clock clock = new Clock();
        clock.addObserver(new Student());
        clock.addObserver(new Teacher());

        clock.lesionOff();
    }
}

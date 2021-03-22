package design.observer;

import lombok.Data;

@Data
public class Student implements Observer<Teacher> {

    private String name;

    @Override
    public void eventListener(Event<Teacher> event) {
        System.out.println("student: " + name + " learn: " + event.getSource().getClassName());
    }
}

package design.observer;

import lombok.Data;

@Data
public class ClassEvent implements Event<Teacher> {
    private Teacher teacher;

    @Override
    public Teacher getSource() {
        return teacher;
    }
}

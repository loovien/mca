package design.observer;

public class Main {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setClassName("math");

        Student studentA = new Student();
        studentA.setName("luowen");
        Student studentB = new Student();
        studentB.setName("maomao");
        teacher.addObserver(studentA).addObserver(studentB);

        ClassEvent classEvent = new ClassEvent();
        classEvent.setTeacher(teacher);
        teacher.publishEvent(classEvent);
    }
}

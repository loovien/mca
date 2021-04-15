package design.factory.entity;

public class Cat {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Cat() {
    }

    private void speak() {
        System.out.println("miaomiao");
    }
}

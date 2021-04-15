package design.factory;

import design.factory.entity.Cat;
import design.factory.entity.Dog;

public class SimpleFactory {

    private SimpleFactory() {
    }

    public static Dog createDog() {
        System.out.println("create a dog");
        return new Dog("hashiqi");
    }

    public static Cat createCat() {
        return new Cat();
    }

}

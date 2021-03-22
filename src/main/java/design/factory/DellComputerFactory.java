package design.factory;

import design.factory.computer.Display;
import design.factory.computer.Engine;
import design.factory.computer.Keyboard;

public class DellComputerFactory extends AbstractComputerFactory {
    @Override
    public Display createDisplay() {
        return new Display() {
            @Override
            public String logo() {
                return "Dell";
            }
        };
    }

    @Override
    public Engine createEngine() {
        return new Engine() {
            @Override
            public String CPU() {
                return "Intel I9";
            }
        };
    }

    @Override
    public Keyboard createKeyboard() {
        return new Keyboard() {
            @Override
            public int keySize() {
                return 108;
            }
        };
    }
}

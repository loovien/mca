package design.factory;

import design.factory.computer.Display;
import design.factory.computer.Engine;
import design.factory.computer.Keyboard;

public abstract class AbstractComputerFactory {

    public abstract Display createDisplay();

    public abstract Engine createEngine();

    public abstract Keyboard createKeyboard();

}

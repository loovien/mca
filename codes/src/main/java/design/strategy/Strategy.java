package design.strategy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Strategy {

    Formatter formatter;

    public Strategy(Formatter formatter) {
        this.formatter = formatter;
    }

    public Strategy() {
        try {
            initializr();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void initializr() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("app.properties"));
        String strategy = (String) properties.get("strategy");
        this.formatter = (Formatter) Class.forName(strategy).getConstructor(null).newInstance();
    }

    public static void main(String[] args) {
        Strategy strategy = new Strategy();
        strategy.formatter.formatter("hello world");
    }


}

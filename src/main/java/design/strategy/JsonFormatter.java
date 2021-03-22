package design.strategy;

public class JsonFormatter implements Formatter {
    @Override
    public void formatter(String body) {
        System.out.println("{\"txt\":" + body + " }");
    }
}

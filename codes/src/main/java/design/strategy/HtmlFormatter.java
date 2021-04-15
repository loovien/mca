package design.strategy;

public class HtmlFormatter implements Formatter {
    @Override
    public void formatter(String body) {
        System.out.println("<h1>" + body + "</h1>");
    }
}

package design.strategy;

public class PDFFormatter implements Formatter {
    @Override
    public void formatter(String body) {
        System.out.println("pdf: " + body);
    }
}

package design.chain.v2;

import design.chain.Request;
import design.chain.Response;

public class UseIt {

    public static void main(String[] args) {
        Request request = new Request();
        request.setBody("xxx:)");

        Response response = new Response();
        response.setResponse("ooo:)");

        HttpChain httpChain = new HttpChain();
        httpChain.add(new Filters.HtmlFilter());
        httpChain.add(new Filters.SensitiveFilter());
        httpChain.doFilter(request, response);

        System.out.println(request.getBody());
        System.out.println("------------------------");
        System.out.println(response.getResponse());
    }
}

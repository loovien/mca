package design.chain.v1;

import design.chain.Request;
import design.chain.Response;

public class UseIt {

    public static void main(String[] args) {
        Request request = new Request();
        request.setBody("hello <script> working 996");

        Response response = new Response();
        response.setResponse("loovien");

        ServletChain servletChain = new ServletChain();
        servletChain.add(new DatelineChain());
        servletChain.add(new HtmlFilter()).add(new SensitiveFilter());
        boolean success = servletChain.doFilter(request, response, servletChain);
        System.out.println(success);
    }
}

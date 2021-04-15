package design.chain.v1;

import design.chain.Request;
import design.chain.Response;

public class HtmlFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response, Chain chain) {
        String body = request.getBody();
        body = body.replace("<", "[");
        body = body.replace(">", "]");
        request.setBody(body);
        System.out.println(body);
        if (!chain.doFilter(request, response, chain)) {
            return false;
        }
        String resp = response.getResponse() + "--------- html";
        System.out.println(resp);
        return true;
    }
}

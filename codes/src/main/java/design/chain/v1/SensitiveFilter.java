package design.chain.v1;

import design.chain.Request;
import design.chain.Response;

public class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response, Chain chain) {
        String body = request.getBody();
        body = body.replace("996", "955");
        System.out.println(body);
        if (!chain.doFilter(request, response, chain)) {
            return false;
        }
        String resp = response.getResponse() + "============== sensitive";
        System.out.println(resp);
        return true;
    }
}

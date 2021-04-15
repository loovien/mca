package design.chain.v2;

import design.chain.Request;
import design.chain.Response;

public class Filters {

    public static class HtmlFilter implements Filter {

        @Override
        public void doFilter(Request request, Response response, Chain chain) {
            request.setBody(request.getBody() + "=== html === ");
            chain.doFilter(request, response);
            response.setResponse(response.getResponse() + "***  html *** ");
        }
    }

    public static class SensitiveFilter implements Filter {
        @Override
        public void doFilter(Request request, Response response, Chain chain) {
            request.setBody(request.getBody() + "=== sensitive ===");
            chain.doFilter(request, response);
            response.setResponse(response.getResponse() + "*** sensitive ***");
        }
    }
}

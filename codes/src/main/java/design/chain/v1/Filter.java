package design.chain.v1;

import design.chain.Request;
import design.chain.Response;

public interface Filter {
    boolean doFilter(Request request, Response response, Chain chain);
}

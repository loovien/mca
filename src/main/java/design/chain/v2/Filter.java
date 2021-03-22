package design.chain.v2;

import design.chain.Request;
import design.chain.Response;

public interface Filter {

    void doFilter(Request request, Response response, Chain chain);

}
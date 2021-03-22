package design.chain.v2;

import design.chain.Request;
import design.chain.Response;

public interface Chain {
    void add(Filter filter);

    void doFilter(Request request, Response response);
}

package design.chain.v1;

import design.chain.Request;
import design.chain.Response;

import java.util.ArrayList;
import java.util.List;

public class ServletChain implements Chain {

    List<Filter> filters = new ArrayList<>();

    private int current = 0;

    @Override
    public boolean doFilter(Request request, Response response, Chain chain) {
        if (current >= filters.size()) {
            return true;
        }
        Filter filter = filters.get(current);
        current++;
        return filter.doFilter(request, response, chain);
    }

    @Override
    public Chain add(Filter filter) {
        filters.add(filter);
        return this;
    }

}

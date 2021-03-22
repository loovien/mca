package design.chain.v2;

import design.chain.Request;
import design.chain.Response;

import java.util.ArrayList;
import java.util.List;

public class HttpChain implements Chain {

    int current = 0;

    List<Filter> filterList = new ArrayList<>();

    @Override
    public void add(Filter filter) {
        filterList.add(filter);
    }

    @Override
    public void doFilter(Request request, Response response) {
        if (current == filterList.size()) return;
        Filter filter = filterList.get(current);
        current++;
        filter.doFilter(request, response, this);
    }
}

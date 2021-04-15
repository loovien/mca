package design.chain.v1;

import design.chain.Request;
import design.chain.Response;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class DatelineChain implements Chain {
    List<Filter> filters = new ArrayList<>();

    @Override
    public Chain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public boolean doFilter(Request request, Response response, Chain chain) {
        int day = LocalDateTime.now().get(ChronoField.DAY_OF_MONTH);
        if (day > 21) {
            chain.doFilter(request, response, chain);
            return true;
        }
        return false;
    }
}

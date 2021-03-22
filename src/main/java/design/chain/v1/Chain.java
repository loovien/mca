package design.chain.v1;

public interface Chain extends Filter {

    Chain add(Filter filter);

}

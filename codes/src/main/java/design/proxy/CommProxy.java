package design.proxy;

/**
 * created 7/13/2021 9:33 AM
 *
 * @author luowen <loovien@163.com>
 */
public class CommProxy {

    public interface Work {
        void weakup();

        void working();

        void sleep();
    }

    private static class RealWorker implements Work {

        private String name;

        public RealWorker(String name) {
            this.name = name;
        }

        @Override
        public void weakup() {
            System.out.println(this.name + " weakup");
        }

        @Override
        public void working() {
            System.out.println(this.name + " working");
        }

        @Override
        public void sleep() {
            System.out.println(this.name + " sleep");
        }
    }

    public static class WorkerProxy implements Work {

        private final Work work;

        public WorkerProxy(String name) {
            this.work = new RealWorker(name);
        }

        @Override
        public void weakup() {
            this.work.weakup();
        }

        @Override
        public void working() {
            this.work.working();
        }

        @Override
        public void sleep() {
            this.work.sleep();
        }
    }


    public static void main(String[] args) {
        Work bigwen = new WorkerProxy("bigwen");
        bigwen.weakup();
        bigwen.working();
        bigwen.sleep();
    }
}

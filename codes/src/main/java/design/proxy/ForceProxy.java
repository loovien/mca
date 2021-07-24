package design.proxy;

/**
 * created 7/13/2021 9:47 AM
 *
 * @author luowen <loovien@163.com>
 */
public class ForceProxy {

    public interface Coding {
        void openIDE();

        void coding();

        void commit();

        void closeIDE();

        Coding getCoder();
    }

    public static class Programmer implements Coding {

        private final String name;

        private Coding coder;

        public Programmer(String name) {
            this.name = name;
        }

        @Override
        public void openIDE() {
            if (isProxy()) {
                System.out.println(this.name + " open Intellij idea");
                return;
            }
            System.out.println(this.name + " proxy must setting.");
        }

        @Override
        public void coding() {
            if (isProxy()) {
                System.out.println(this.name + " is hard coding");
                return;
            }
            System.out.println(this.name + " proxy must setting.");
        }

        @Override
        public void commit() {
            if (isProxy()) {
                System.out.println(this.name + " commit and push to git repository.");
                return;
            }
            System.out.println(this.name + " proxy must setting.");
        }

        @Override
        public void closeIDE() {
            if (isProxy()) {
                System.out.println(this.name + " close intellij idea and work off.");
                return;
            }
            System.out.println(this.name + " proxy must setting.");
        }

        @Override
        public Coding getCoder() {
            this.coder = new ProgrammerProxy(this);
            return this.coder;
        }

        public boolean isProxy() {
            return this.coder != null;
        }
    }

    public static class ProgrammerProxy implements Coding {

        private final Coding coder;

        public ProgrammerProxy(Coding coding) {
            this.coder = coding;
        }

        @Override
        public void openIDE() {
            coder.openIDE();
        }

        @Override
        public void coding() {
            coder.coding();
        }

        @Override
        public void commit() {
            coder.commit();
        }

        @Override
        public void closeIDE() {
            coder.closeIDE();
        }

        @Override
        public Coding getCoder() {
            return this;
        }
    }

    public static void main(String[] args) {
        Programmer bigwen = new Programmer("bigwen");
        Coding coder = bigwen.getCoder(); // 找下代理来干活
        coder.openIDE();
        coder.coding();
        coder.commit();
        coder.closeIDE();
    }
}

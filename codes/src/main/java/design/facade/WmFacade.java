package design.facade;

/**
 * created 7/14/2021 11:09 AM
 *
 * @author luowen <loovien@163.com>
 */
public class WmFacade {

    private class Wm {
        protected void weakup() {
            System.out.println("weakup");
        }

        private void breadfirst() {
            System.out.println("breadfirst");
        }

        private void go() {
            System.out.println("go");
        }

    }

    public static class Police {
        public void checking() {
            System.out.println("police checking");
        }
    }

    private final Wm wm = new Wm();

    private final Police police = new Police();

    public void travel() {
        wm.weakup();
        wm.breadfirst();
        police.checking();
        wm.go();
    }

    public static void main(String[] args) {
        WmFacade a = new WmFacade();
        a.travel();
    }
}

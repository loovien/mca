package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class DataListener implements Watcher, AsyncCallback.DataCallback {


    private String node;

    public String getResult() {
        return result;
    }

    private String result;

    private final ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch = new CountDownLatch(1);


    public DataListener(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
        result = new String(bytes);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("=============================================" + watchedEvent);
        switch (watchedEvent.getType()) {
            case None:
                System.out.println("node none!!!");
                break;
            case NodeCreated:
                System.out.println("node not created");
                try {
                    zooKeeper.create(this.node, "luowen".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case NodeDeleted:
                System.out.println("node deleted!!!");
                result = "DELETED";
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                System.out.println("node data changed!!!");
                zooKeeper.getData(this.node, this, this, result);
                countDownLatch.countDown();
                break;
            case NodeChildrenChanged:
                System.out.println("node children changed!!!");
                break;
            case DataWatchRemoved:
                System.out.println("node watch removed!!!");
                break;
            case ChildWatchRemoved:
                System.out.println("child watched removed!!!");
                break;
            case PersistentWatchRemoved:
                System.out.println("persistent watch removed!!!");
                break;
        }
    }

    public void dataWatch(String node) throws KeeperException, InterruptedException {
        this.node = node;
        System.out.println("=============================================");
        zooKeeper.exists(node, this);
        countDownLatch.await();
    }
}

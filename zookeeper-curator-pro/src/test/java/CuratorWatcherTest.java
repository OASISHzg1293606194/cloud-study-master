import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-06 16:08
 */
public class CuratorWatcherTest {

    private CuratorFramework curatorFramework = null;

    @Before
    public void initCuratorFramework() {
        String connectString = "192.168.213.225:2181";
        int sessionTimeoutMs = 60 * 1000;
        int connectionTimeoutMs = 15 * 1000;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
        curatorFramework = CuratorFrameworkFactory.newClient(connectString, sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
        curatorFramework.start();
        System.out.println("========================Test Before zookeeper客户端到服务端连接创建完成");
    }

    @After
    public void closeCuratorFramework() {
        if (curatorFramework != null) {
            curatorFramework.close();
            System.out.println("========================Test After zookeeper客户端到服务端连接关闭成功");
        }
    }

    /**
     * watcher监听-NodeCache
     * <p/>
     * 监听指定节点的变化
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2022/1/7/007 13:59
     */
    @Test
    public void nodeCache() {
        /**
         * @param client            curator client实例
         * @param path              znode节点路径
         * @param dataIsCompressed  数据是否进行压缩，默认不压缩，存入压缩数据在读取时要解压缩
         */
        try {
            // 1、创建NodeCache对象
            NodeCache nodeCache = new NodeCache(curatorFramework, "/testWatcher", Boolean.FALSE.booleanValue());
            // 2、注册监听【增删改/testWatcher节点都会触发监听事件】
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("========================节点变化了========================");
                    ChildData data = nodeCache.getCurrentData();
                    System.out.println("------------------------" + new String(data.getData()));
                }
            });
            // 3、开启监听，如果设置为true则开启监听时加载缓冲数据
            nodeCache.start(Boolean.TRUE.booleanValue());
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * watcher监听-PathChildrenCache
     * <p/>
     * 监听指定节点的儿子节点
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2022/1/7/007 13:59
     */
    @Test
    public void pathChildrenCache() {
        /**
         * @param client           curator client实例
         * @param path             znode节点路径
         * @param cacheData        是否缓存状态数据
         * @param dataIsCompressed 数据是否进行压缩，默认不压缩，存入压缩数据在读取时要解压缩
         * @param executorService  线程池
         */
        try {
            // 1、创建PathChildrenCache对象
            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/testWatcher", Boolean.TRUE.booleanValue());
            // 2、绑定监听器【增删改/testWatcher节点的儿子节点都会触发监听事件】
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    System.out.println("========================子节点变化了========================");
                    System.out.println(event);
                    PathChildrenCacheEvent.Type type = event.getType();
                    ChildData data = event.getData();
                    if (PathChildrenCacheEvent.Type.CHILD_ADDED.equals(type)) {
                        System.out.println("新增子节点------------------------" + new String(data.getData()));
                    } else if (PathChildrenCacheEvent.Type.CHILD_UPDATED.equals(type)) {
                        System.out.println("修改子节点------------------------" + new String(data.getData()));
                    } else if (PathChildrenCacheEvent.Type.CHILD_REMOVED.equals(type)) {
                        System.out.println("删除子节点------------------------" + new String(data.getData()));
                    } else {
                    }
                }
            });
            // 3、开启监听，如果设置为true则开启监听时加载缓冲数据
            pathChildrenCache.start(Boolean.TRUE.booleanValue());
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * watcher监听-TreeCache
     * <p/>
     * 监听指定节点及其儿子节点
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2022/1/7/007 13:59
     */
    @Test
    public void treeCache() {
        /**
         * @param client            curator client实例
         * @param path              znode节点路径
         * @param cacheData         是否缓存状态数据
         * @param dataIsCompressed  数据是否进行压缩，默认不压缩，存入压缩数据在读取时要解压缩
         * @param maxDepth          节点最大深度，Integer.MAX_VALUE
         * @param executorService   线程池
         * @param createParentNodes
         * @param selector
         */
        try {
            // 1、创建TreeCache对象
            TreeCache treeCache = new TreeCache(curatorFramework, "/testWatcher");
            // 2、绑定监听器【增删改/testWatcher节点及其子节点都会触发监听事件】
            treeCache.getListenable().addListener(new TreeCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                    System.out.println("========================节点变化了========================");
                    System.out.println(event);
                    TreeCacheEvent.Type type = event.getType();
                    ChildData data = event.getData();
                    if (TreeCacheEvent.Type.NODE_ADDED.equals(type)) {
                        System.out.println("新增节点------------------------" + new String(data.getData()));
                    } else if (TreeCacheEvent.Type.NODE_UPDATED.equals(type)) {
                        System.out.println("修改节点------------------------" + new String(data.getData()));
                    } else if (TreeCacheEvent.Type.NODE_REMOVED.equals(type)) {
                        System.out.println("删除节点------------------------" + new String(data.getData()));
                    } else {
                    }
                }
            });
            // 3、开启监听
            treeCache.start();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

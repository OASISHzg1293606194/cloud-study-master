import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.jboss.netty.util.CharsetUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-06 16:08
 */
public class CuratorTest {

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
     * 创建zookeeper连接
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2022/1/6/006 16:09
     */
    @Test
    public void createConnect() {
        /**
         * @param connectString       连接字符串，zookeeper server地址和端口号 "192.168.213.225:2181,192.168.213.224:2181"
         * @param sessionTimeoutMs    会话超时时间(客户端与服务端会话超时时间)，单位ms
         * @param connectionTimeoutMs 连接超时时间，单位ms
         * @param retryPolicy         重试策略，使用RetryPolicy接口的实现类
         * @param namespace           命名空间，指定只操作该节点
         */
        String connectString = "192.168.213.225:2181";
        int sessionTimeoutMs = 60 * 1000;
        int connectionTimeoutMs = 15 * 1000;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
        String namespace = "oasis";

        // 第一种方式
        CuratorFramework curator1 = CuratorFrameworkFactory.newClient(connectString, sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
        curator1.start();

        // 第二种方式
        CuratorFramework curator2 = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .build();
        curator2.start();
        try {
            curator2.create().forPath("/heaven", (new String()).getBytes(CharsetUtil.UTF_8));
            curator2.create().forPath("/heaven/haven", (new String()).getBytes(CharsetUtil.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建zookeeper节点
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2022/1/6/006 17:56
     */
    @Test
    public void createZnode() {
        try {
            // 1、创建基本的znode【此时没有指定数据会将当前客户端ip作为数据存储】
            String path1 = curatorFramework.create().forPath("/hazy");
            System.out.println(path1);
            // 2、创建带数据的znode
            String path2 = curatorFramework.create().forPath("/hazy/spark", (new String("烟火")).getBytes(CharsetUtil.UTF_8));
            System.out.println(path2);
            // 3、分别创建临时、有序、临时有序的znode
            // 此处创建的临时节点都会在会话关闭后删除
            String path3 = curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/sequential");
            System.out.println(path3);
            String path4 = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/ephemeral");
            System.out.println(path4);
            String path5 = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/ephemeralsequential");
            System.out.println(path5);
            // 4、创建多级节点
            String path6 = curatorFramework.create().creatingParentsIfNeeded().forPath("/move/spark", (new String("烟火")).getBytes(CharsetUtil.UTF_8));
            System.out.println(path6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改zookeeper节点
     * <p/>
     *
     * @return void
     * @throws
     * @author HuangZhiGao
     * @date 2022/1/6/006 18:36
     */
    @Test
    public void updateZnode() {
        try {
            // 1、修改znode存储的数据
            curatorFramework.setData().forPath("/move", (new String("雪中悍刀行")).getBytes(CharsetUtil.UTF_8));
            // 2、根据version修改znode存储的数据，如果修改时版本不一致会修改失败-报错
            Stat stat = new Stat();
            curatorFramework.getData().storingStatIn(stat).forPath("/move");
            int version = stat.getVersion();
            curatorFramework.setData().withVersion(version).forPath("/move", (new String("秦时明月")).getBytes(CharsetUtil.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除zookeeper节点
     * <p/>
     *
     * @return void
     * @throws
     * @author HuangZhiGao
     * @date 2022/1/6/006 18:36
     */
    @Test
    public void deleteZnode() {
        try {
            // 1、删除单个znode节点
            curatorFramework.delete().forPath("/hazy/spark");
            curatorFramework.create().creatingParentsIfNeeded().forPath("/hazy/spark", (new String("烟火")).getBytes(CharsetUtil.UTF_8));
            // 2、删除当前znode节点以及当前节点下的所有子节点
            curatorFramework.delete().deletingChildrenIfNeeded().forPath("/hazy");
            curatorFramework.create().creatingParentsIfNeeded().forPath("/hazy/spark", (new String("烟火")).getBytes(CharsetUtil.UTF_8));
            // 3、必须成功的删除znode节点【重试-防止网络抖动】
            curatorFramework.delete().guaranteed().forPath("/hazy/spark");
            curatorFramework.create().creatingParentsIfNeeded().forPath("/hazy/spark", (new String("烟火")).getBytes(CharsetUtil.UTF_8));
            // 4、删除后回调
            curatorFramework.delete().guaranteed().inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                    System.out.println("========================znode is deleted");
                    System.out.println(event);
                }
            }).forPath("/hazy/spark");
            curatorFramework.create().creatingParentsIfNeeded().forPath("/hazy/spark", (new String("烟火")).getBytes(CharsetUtil.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询zookeeper节点
     * <p/>
     *
     * @return void
     * @throws
     * @author HuangZhiGao
     * @date 2022/1/6/006 18:36
     */
    @Test
    public void getZnode() {
        try {
            // 1、查询节点数据
            byte[] bytes = curatorFramework.getData().forPath("/move");
            System.out.println(new String(bytes));
            // 2、查询子节点
            List<String> list = curatorFramework.getChildren().forPath("/move");
            System.out.println(list);
            // 3、查询节点详细信息
            Stat stat = new Stat();
            curatorFramework.getData().storingStatIn(stat).forPath("/move");
            System.out.println(stat.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

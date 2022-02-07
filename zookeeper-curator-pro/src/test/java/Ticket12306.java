import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.StandardLockInternalsDriver;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;


/**
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-07 17:32
 */
public class Ticket12306 implements Runnable {

    /**
     * 模拟数据库票数量
     */
    private int ticket = 15;

    /**
     * lock节点路径
     */
    private static final String LOCK_PATH = "/lock";

    /**
     * curator client初始化信息
     */
    private String connectString = "192.168.213.225:2181";
    private int sessionTimeoutMs = 60 * 1000;
    private int connectionTimeoutMs = 15 * 1000;
    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);

    /**
     * 分布式可重入排它锁
     */
    private InterProcessMutex lock;

    /**
     * curator client实例
     */
    private CuratorFramework curatorFramework;


    public Ticket12306() {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(retryPolicy)
                .build();
        curatorFramework.start();
        /**
         * @param client curator client实例
         * @param path   lock节点路径
         * @param driver lock driver
         */
        lock = new InterProcessMutex(curatorFramework, LOCK_PATH, new StandardLockInternalsDriver());
    }

    @Override
    public void run() {
        while (true) {
            // 获取锁
            try {
                // 设置获取锁等待时间
                lock.acquire(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (ticket < 1) {
                    System.out.println("stop selling========================" + ticket);
                    break;
                } else {
                    System.out.println("on sale========================" + Thread.currentThread() + ":" + ticket);
                    ticket--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

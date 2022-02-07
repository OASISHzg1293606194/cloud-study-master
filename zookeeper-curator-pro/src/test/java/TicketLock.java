
/**
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-07 17:42
 */
public class TicketLock {
    public static void main(String[] args) {
        Ticket12306 ticket12306 = new Ticket12306();
        // 创建客户端
        Thread thread1 = new Thread(ticket12306, "携程");
        Thread thread2 = new Thread(ticket12306, "飞猪");
        Thread thread3 = new Thread(ticket12306, "去哪儿");
        /*未加锁时会出现超卖现象*/
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

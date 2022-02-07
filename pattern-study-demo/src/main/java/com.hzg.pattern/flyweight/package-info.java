/**
 * @Package: com.hzg.pattern.flyweight
 * @Description: 享元模式(轻量级模式)[Flyweight Pattern]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-12 11:01
 * 定义：<br/>
 * 是对象池的一种实现<br/>
 * 类似于线程池，线程池可以避免线程的不断创建和销毁而带来的性能消耗<br/>
 * 提供了减少对象数量从而改善应用所需的对象结构的方式<br/>
 * 特征：宗旨：共享细粒度对象，将多个对同一对象的访问集中起来，属于结构型模式<br/>
 * 适用场景：<br/>
 * 常常应用于系统底层的开发，以便解决系统的性能问题<br/>
 * 系统有大量相似对象、需要缓冲池的场景<br/>
 * 源码场景：String常量池，Integer、Long二者的valueOf方法可以看到使用了缓存<br/>
 * <p/>
 * 优点：<br/>
 * <ol>
 * <li>减少对象的创建，降低内存中对象的数量，降低系统内存，提高效率</li>
 * <li>减少内存之外其他资源的占用</li>
 * </ol>
 * <p/>
 * 缺点：<br/>
 * <ol>
 * <li>关注内、外部状态，关注线程安全问题</li>
 * <li>使得系统、程序的逻辑复杂化</li>
 * </ol>
 */
package com.hzg.pattern.flyweight;
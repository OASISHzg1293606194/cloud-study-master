/**
 * @Package: com.hzg.principle.carp
 * @Description: 合成复用原则[Composite/Aggregate Reuse Principle CARP]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 18:10
 * 合成复用原则定义：<br/>
 * 指尽量使用对象组合(has-a)/聚合(contains-a)而不是继承关系达到软件复用的目的<br/>
 * 继承叫做白箱复用，相当于把所有的实现细节暴露给子类<br/>
 * 组合/聚合称为黑箱复用，我们是无法获取到类以外的对象的实现细节的<br/>
 * <p/>
 * 合成复用原则作用：<br/>
 * <ol>
 * <li>提高系统灵活性</li>
 * <li>降低类之间的耦合度</li>
 * </ol>
 */
package com.hzg.principle.carp;
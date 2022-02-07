/**
 * @Package: com.hzg.principle.lkp
 * @Description: 最少知道原则[Least Knowledge Principle LKP]
 * @Description: 迪米特法则[Law of Demeter LoD]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 16:57
 * 最少知道原则定义：<br/>
 * 指一个对象应该对其他对象保持最少的了解<br/>
 * 强调：只和朋友交流，不和陌生人说话<br/>
 * 成员朋友类：出现在成员变量、方法入参、方法出参中的类<br/>
 * 非成员朋友类：出现在方法体内部的类<br/>
 * <p/>
 * 最少知道原则作用：<br/>
 * <ol>
 * <li>降低类之间的耦合度</li>
 * </ol>
 */
package com.hzg.principle.lkp;
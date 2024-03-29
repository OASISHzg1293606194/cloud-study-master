/**
 * @Package: com.hzg.principle.lsp
 * @Description: 里氏替换原则[Liskov Substitution Principle LSP]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 17:29
 * 里氏替换原则定义：<br/>
 * 指如果每个类型为T1的对象O1，都有类型为T2的对象O2，
 * 使得以T1定义的所有程序P在所有对象O1都替换成O2时，
 * 程序P的行为没有发生变化，那么类型T2是类型T1的子类型<br/>
 * <p/>
 * 可以理解为一个软件实体如果适用于一个父类，那么一定适用于其子类，
 * 所有引用父类的地方必须能透明的使用其子类的对象，
 * 子类对象能够替换父类对象，而程序逻辑不变<br/>
 * <p/>
 * 引申含义：子类可以扩展父类的功能，但不能改变父类原有的功能<br/>
 * <ol>
 * <li>子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法</li>
 * <li>子类可以增加自己的特有方法</li>
 * <li>当子类的方法重载父类的方法时，方法的前置条件(即方法的输入/入参)要比父类方法的输入参数更宽松</li>
 * <li>当子类的方法实现父类的方法时(重写/重载或实现抽象方法)，方法的后置条件(即方法的输出/返回值)要比父类更严格或者一样</li>
 * </ol>
 * <p/>
 * 里氏替换原则优点：<br/>
 * <ol>
 * <li>约束继承泛滥，是开闭原则的一种体现</li>
 * <li>加强程序健壮性，变更时有更好的兼容性(降低变更风险)</li>
 * <li>提高程序可维护性和可扩展性</li>
 * </ol>
 */
package com.hzg.principle.lsp;
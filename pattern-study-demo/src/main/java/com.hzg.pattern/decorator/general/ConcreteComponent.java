package com.hzg.pattern.decorator.general;

/**
 * @Package: com.hzg.pattern.decorator.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 14:18
 */
public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("标准实现");
    }
}

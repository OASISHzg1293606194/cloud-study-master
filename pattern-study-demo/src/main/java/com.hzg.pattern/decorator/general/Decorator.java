package com.hzg.pattern.decorator.general;

/**
 * @Package: com.hzg.pattern.decorator.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 14:16
 */
public abstract class Decorator extends Component {

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        this.component.operation();
    }

}

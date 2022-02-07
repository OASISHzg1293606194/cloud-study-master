package com.hzg.pattern.flyweight.general;

/**
 * @Package: com.hzg.pattern.flyweight.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-12 15:43
 */
public class ConcreteFlyweight implements IFlyweight {

    private String extrinsicState;

    public ConcreteFlyweight(String extrinsicState) {
        this.extrinsicState = extrinsicState;
    }

    /**
     * 因为内部状态具备不变性，所以作为缓存key
     */
    @Override
    public void operation(String extrinsicState) {
        System.out.println("Object Address:" + System.identityHashCode(this));
        System.out.println("IntrinsicState:" + this.extrinsicState);
        System.out.println("ExtrinsicState:" + extrinsicState);
    }

}

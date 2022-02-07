package com.hzg.interview.first.clazzinit;

/**
 * @Package: com.hzg.interview.first.clazzinit
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-05 17:36
 */
public class Father {

    private int i = test();
    private static int j = method();


    static {
        System.out.print("(1)");
    }

    Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }

}

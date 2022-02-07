package com.hzg.interview.first;

/**
 * @Package: com.hzg.interview.first
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-05 20:41
 */
public class NStep {

    public static int recursion(int nStep) {
        if (nStep < 1) {
            throw new IllegalArgumentException(nStep + "不能小于1");
        }
        if (nStep == 1 || nStep == 2) {
            return nStep;
        }
        return recursion(nStep - 2) + recursion(nStep - 1);
    }

    public static int iteration(int nStep) {
        if (nStep < 1) {
            throw new IllegalArgumentException(nStep + "不能小于1");
        }
        if (nStep == 1 || nStep == 2) {
            return nStep;
        }

        int one = 2;
        int two = 1;
        int sum = 0;

        for (int i = 3; i <= nStep; i++) {
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println(recursion(40));
        long end = System.currentTimeMillis();
        System.out.println(end - begin);

        long start = System.currentTimeMillis();
        System.out.println(iteration(40));
        long complete = System.currentTimeMillis();
        System.out.println(start - complete);
    }

}

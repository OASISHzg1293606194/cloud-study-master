package com.hzg.interview.first;

import java.util.Arrays;

/**
 * @Package: com.hzg.interview.first
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-05 19:16
 */
public class MethodTransmitArgument {
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1, 2, 3, 4, 5};
        MyData myData = new MyData();

        change(i, str, num, arr, myData);

        System.out.println("i=" + i);
        System.out.println("str=" + str);
        System.out.println("num=" + num);
        System.out.println("arr=" + Arrays.toString(arr));
        System.out.println("myData.a=" + myData.a);
        // i=1
        // str=hello
        // num=200
        // arr=[2, 2, 3, 4, 5]
        // myData.a=11
    }

    public static void change(int j, String s, Integer n, int[] a, MyData m) {
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }

}

class MyData {
    int a = 10;
}

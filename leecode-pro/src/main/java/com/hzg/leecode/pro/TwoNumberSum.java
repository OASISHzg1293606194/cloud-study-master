package com.hzg.leecode.pro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.hzg.leecode.pro
 * @Description: 两数之和
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-20 18:23
 */
public class TwoNumberSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoNumberSum(new int[]{2, 3, 9, 10, 8}, 10)));
        System.out.println(Arrays.toString(twoNumberSum_DoubleLoop(new int[]{2, 3, 9, 10, 8}, 10)));
    }

    /**
     * 两数之和
     * <p/>
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出 和 为目标值的那两个整数，并返回他们的数组下标。
     * <p/>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p/>
     *
     * @param nums
     * @param target
     * @return int[]
     */
    public static int[] twoNumberSum(int[] nums, int target) {
        int[] rslt = new int[2];
        if (nums == null || nums.length == 0) {
            return rslt;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                rslt[1] = i;
                rslt[0] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return rslt;
    }


    /**
     * 双层for循环暴力遍历
     * <p/>
     *
     * @param nums
     * @param target
     * @return int[]
     */
    public static int[] twoNumberSum_DoubleLoop(int[] nums, int target) {
        int[] rslt = new int[2];
        if (nums == null || nums.length == 0) {
            return rslt;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    rslt[0] = i;
                    rslt[1] = j;
                    return rslt;
                }
            }
        }
        return rslt;
    }

}

package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
//        int count = searchInsert(new int[]{1,3,5,6},5);


//        int count = countDigitOne(850);
//        System.out.println(count);

//        canFinish(5, new int[][]{{1,0},{0,1}});
//        sortColors(new int[]{2,0,2,1,1,0});
//        candy(new int[]{1,2,87,87,87,2,1});
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    //中215
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        return list.get(list.size()-k);
    }

    //困135
    public int candy(int[] ratings){
        int len = ratings.length;
        int i = len-1;
        for (int k = 0; k < i; k++){
            if (ratings[k] != ratings[k+1]){
                len++;
                if (ratings[k+1] > ratings[k]){
                    k++;
                }
            }
        }
        return len;
    }

    //中等 75. 颜色分类
    public void sortColors(int[] nums) {
        nums = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList()).stream().mapToInt(Integer::intValue).toArray();
        System.out.println(nums);
    }

    //中等 207. 课程表
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean num = true;

        return num;
    }

    //困难 233. 数字 1 的个数
    public int countDigitOne(int n) {
        int num = 0;
        for (int i = 0; i <= n; i++) {
            num = num + String.valueOf(i).length() - String.valueOf(i).replaceAll("1", "").length();
        }
        return num;
    }

    public int searchInsert(int[] nums, int target) {
        List<Integer> list =  Arrays.stream(nums).boxed().collect(Collectors.toList());
        String num = "没有找到目标";
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i)==target){
                num = String.valueOf(i);
            }
        }
        if (num.equals("没有找到目标")){
            list.add(target);
            Collections.sort(list);
            for (int i = 0; i <list.size() ; i++) {
                if (list.get(i)==target){
                    num = String.valueOf(i);
                }
            }
        }
        return Integer.parseInt(num);
    }


    public int singleNumber(int[] nums) {
        List<Integer> list =  Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> collect = list.stream().filter(i -> !Objects.isNull(i))
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum))
                .entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        if (collect.size()>0) {
            list.removeAll(collect);
        }
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i <arr.length ; i++) {
            return arr[i];
        }
        return 0;
    }
}

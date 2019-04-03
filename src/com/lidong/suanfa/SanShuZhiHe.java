package com.lidong.suanfa;

import java.util.ArrayList;
import java.util.List;

public class SanShuZhiHe {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> integerList : lists) {
            for (Integer integer : integerList) {
                System.out.print(integer + "\t");
            }
            System.out.println();
        }
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        quickSort(nums, 0, nums.length - 1);

        int low = 0, high = nums.length - 1;
        while (low < high) {

            if (nums[low] > 0 || nums[high] < 0) {
                return null;
            }
            int temp = nums[low] + nums[high];
            if ((nums[low] + temp) > 0) {
                //System.out.println("continue4");
                high--;
                continue;
            }
            if ((temp + nums[high]) < 0) {
                //System.out.println("continue3");
                low++;
                continue;
            }

            for (int i = low + 1; i < high; i++) {
                if ((nums[i] + temp) == 0) {
                    integerList.add(nums[low]);
                    integerList.add(nums[i]);
                    integerList.add(nums[high]);
                    lists.add(integerList);
                    integerList.clear();
                }
                //System.out.println("continue2");
            }

            if (temp >= 0) low++;
            else high--;

            //System.out.println("continue");
        }
        return lists;
    }

    public static void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int middle = getMiddle(nums, low, high);
            quickSort(nums, low, middle - 1);
            quickSort(nums, middle + 1, high);
        }
    }

    private static int getMiddle(int[] nums, int low, int high) {
        int base = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= base) {
                high--;
            }
            nums[low] = nums[high];

            while (low < high && nums[low] <= base) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = base;
        return low;
    }


}

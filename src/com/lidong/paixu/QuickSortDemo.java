package com.lidong.paixu;

/*
基本思想： 1）选择一个基准元素,通常选择第一个元素或者最后一个元素,
2）通过一趟排序讲待排序的记录分割成独立的两部分，其中一部分记录的元素值均比基准元素值小。另一部分记录的 元素值比基准值大。
3）此时基准元素在其排好序后的正确位置
4）然后分别对这两部分记录用同样的方法继续进行排序，直到整个序列有序。

示例：                   原始： 49 38 65 97 76 13 27 49      （基数选取第一个49）
第一次排序第一次交换：  27 38 65 97 76 13    49 
第一次排序第二次交换：  27 38    97 76 13 65 49
第一次排序第四次交换：  27 38 13    76 97 65 49

第一次排序结束 ： {27 38 13} （49） { 76 97 65 49 }

接着  {27 38 13}  和  { 76 97 65 49 } 进行快速排序

第二次排序结束 ： {13} （27） {38}  (49)  {49 65}  (76)  {97}
第三次排序结束 ： (13) （27） (38)  (49)  (49)  {65}  (76)  (97)
第四次排序结束 ： (13) （27） (38)  (49)  (49)  （65）  (76)  (97)    此时无法再次进行排序。结束。
*/

public class QuickSortDemo extends ArrayResource {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 49, 38, 65, 97, 76, 13, 27, 49 };
		QuickSortDemo demo = new QuickSortDemo();
		demo.quickSortDem(array, 0, array.length - 1);
		printArray(array);
	}

	private void quickSortDem(int[] arry, int low, int high) {
		if (low < high) {
			int middle = getMiddle(arry, low, high);
			quickSortDem(arry, low, middle - 1);
			quickSortDem(arry, middle + 1, high);
		}
	}

	private int getMiddle(int[] array, int low, int high) {
		int base = array[low];
		while (low < high) {
			while (low < high && array[high] >= base) {
				high--;
			}
			array[low] = array[high];
			while (low < high && array[low] <= base) {
				low++;
			}
			array[high] = array[low];
		}
		array[low] = base;
		return low;
	}
}

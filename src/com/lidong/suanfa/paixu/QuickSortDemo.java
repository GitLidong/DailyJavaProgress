package com.lidong.suanfa.paixu;

/*
基本思想： 1）选择一个基准元素,通常选择第一个元素或者最后一个元素,
2）通过一趟排序讲待排序的记录分割成独立的两部分，其中一部分记录的元素值均比基准元素值小。另一部分记录的 元素值比基准值大。
3）此时基准元素在其排好序后的正确位置
4）然后分别对这两部分记录用同样的方法继续进行排序，直到整个序列有序。

示例：          原始  49 38 65 97 76 13 27 49      （基数选取第一个49）
第一次排序第一次交换：  27 38 65 97 76 13    49 
第一次排序第二次交换：  27 38    97 76 13 65 49
第一次排序第四次交换：  27 38 13    76 97 65 49

第一次排序结束 ： {27 38 13} （49） { 76 97 65 49 }

接着  {27 38 13}  和  { 76 97 65 49 } 进行快速排序

第二次排序结束 ： {13} （27） {38}  (49)  {49 65}  (76)  {97}
第三次排序结束 ： (13) （27） (38)  (49)  (49)  {65}  (76)  (97)
第四次排序结束 ： (13) （27） (38)  (49)  (49)  （65）  (76)  (97)    此时无法再次进行排序。结束。

 分析：
快速排序是通常被认为在同数量级（O(nlog2n)）的排序方法中平均性能最好的。
但若初始序列按关键码有序或基本有序时，快排序反而蜕化为冒泡排序。
为改进之，通常以“三者取中法”来选取基准记录，即将排序区间的两个端点与中点三个记录关键码居中的调整为支点记录。

快速排序是一个不稳定的排序方法。

-
*/

public class QuickSortDemo extends ArrayResource {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 49, 38, 65, 97, 76, 13, 27, 49 };
		QuickSortDemo demo = new QuickSortDemo();
		// demo.quickSort(array, 0, array.length - 1);
		// demo.quickSortImprove(array, 0, array.length - 1);
		demo.lidongQuickSordemo(array, 0, array.length - 1);
		printArray(array);
	}

	private void quickSort(int[] arry, int low, int high) {
		if (low < high) {
			int middle = getMiddle(arry, low, high);
			quickSort(arry, low, middle - 1);
			quickSort(arry, middle + 1, high);
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

	/**
	 * 在本改进算法中,只对长度大于k的子序列递归调用快速排序,让原序列基本有序，然后再对整个基本有序序列用插入排序算法排序。
	 * 实践证明，改进后的算法时间复杂度有所降低，且当k取值为 8 左右时,改进算法的性能最佳。
	 */
	private void quickSortImprove(int[] array, int low, int high) {
		if ((high - low) > 8) {
			int middle = getMiddle(array, low, high);
			quickSortImprove(array, low, middle - 1);
			quickSortImprove(array, middle + 1, high);
		} else {
			for (int i = 1; i < array.length; i++) {
				straightInsert(array);
			}
		}
	}

	private void straightInsert(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int aim = array[i];
			int j;
			for (j = i; j > 0 && array[j - 1] > aim; j--) {
				array[j] = array[j - 1];
			}
			array[j] = aim;
		}
	}

	// 0，（ 49） 38 65 97 76 13 27 （49）
	// 1， 49 38 （27） 97 76 13 （65） 49
	// 2， 49 38 27 （13） 76 （97） 65 49
	// 3， 13 38 27 （49) 76 97 65 49
	
	// 4， (13) 38 27 (49) 76 49 65 97
	// 5, 13 27 38 49 65 49 76 97
	// 6, 13 27 49 49 65 76 97 
	
	private void lidongQuickSordemo(int[] array, int left, int right) {
		int i, j, temp, base;
		if (left > right) {
			return;
		}
		base = array[left];
		i = left;
		j = right;
		while (i != j) {
			while (array[j] >= base && j > i) {
				j--;
			}
			while (array[i] <= base && j > i) {
				i++;
			}
			if (i < j) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		array[left] = array[i];
		array[i] = base;
		lidongQuickSordemo(array, left, i - 1);
		lidongQuickSordemo(array, i + 1, right);
	}

}

package com.lidong.paixu;

/**
 *  基本思想:
* 将一个记录插入到已排序好的有序表中，从而得到一个新，记录数增1的有序表。
* 即：先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
 * 要点：设立哨兵，作为临时存储和判断数组边界之用。
 *   直接插入排序示例：
 * @author LiDong
 * *	直接插入排序 
 *    (49) 38 65 97 76 13 27 49 
 * 1. (38 49) 65 97 76 13 27 49 
 * 2. (38 49 65) 97 76 13 27 49 
 * 3. (38 49 65 97) 76 13 27 49 
 * 4. (38 49 65 76 97) 13 27 49 
 * 5. (13 38 49 65 76 97) 27 49 
 * 6. (13 27 38 49 65 76 97) 49 
 * 7. (13 27 38 49 49 65 76 97)
 * 
 *  如果碰见一个和插入元素相等的，那么插入元素把想插入的元素放在相等元素的后面。
 *  所以，相等元素的前后顺序没有改变，从原无序序列出去的顺序就是排好序后的顺序，所以插入排序是稳定的。
 *  
 *   ************ 时间复杂度：O（n^2） *********************
 */

public class StraigthInsertSortDemo extends ArrayResource{

	public static void main(String[] args) {
		int [] array = {49, 38, 65, 97, 76, 13, 27, 49};
		StraigthInsertSortDemo demo = new StraigthInsertSortDemo();
		printArray(array);
		demo.StraigthInsertSort(array);
		printArray(array);
 	}

	private void StraigthInsertSort(int[] array) {
		for (int i = 1; i < array.length; i++) { // 从头部第一个当做已经排好序的，把后面的一个一个的插到已经排好的列表中去
			int j;
			int aim = array[i]; // aim 为待插入元素  
			for (j = i; j > 0 && aim < array[j - 1]; j--) {
				array[j] = array[j - 1]; // 通过循环，逐个后移一位找到要插入的位置。
			}
			array[j] = aim; // 插入 
		}
	}

}

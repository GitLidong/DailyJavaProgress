package com.lidong.paixu.sort2;

/**
 * 
 * @author Dong
 *	插入排序之直接插入排序
 *
 *	时间复杂度： 最坏 O(N Log N)  最好  O(N Log N)  平均  O(N Log N)
 *	空间复杂度： 辅助存储 O(1)
 *	稳定性：        不稳定
 */


//堆排序是将数据看成是完全二叉树、根据完全二叉树的特性来进行排序的一种算法
//最大堆要求节点的元素都要不小于其孩子，最小堆要求节点元素都不大于其左右孩子
//那么处于最大堆的根节点的元素一定是这个堆中的最大值
//
//完全二叉树有个特性：左边子节点位置 = 当前父节点的两倍 + 1，右边子节点位置 = 当前父节点的两倍 + 2

public class HeapSortDemo {

	public static void main(String[] args) {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		HeapSortDemo demp = new HeapSortDemo();
		demp.heapSort(array);
		Utils.printArray(array);
	}

	// 堆排序
	
	public int[] heapSort(int[] array) {
		array = buildMaxHep(array); // 初始建堆，array[0]为第一趟值最大的元素
		for (int i = array.length - 1; i > 0; i--) {
			int temp = array[0]; // 将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
			array[0] = array[i];
			array[i] = temp;
			adjustDownToUp(array, 0, i); // 整理，将剩余的元素整理成堆
		}
		System.out.print("After heap Sort: ");
		return array;
	}

	// 构建大根堆：将array看成完全二叉树的顺序存储结构
	private int[] buildMaxHep(int[] array) {
		// 从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
		for (int i = (array.length - 1 - 1) / 2; i >= 0; i--) {
			adjustDownToUp(array, i, array.length);
		}
		return array;
	}

	private void adjustDownToUp(int[] array, int parent, int length) {
		int temp = array[parent];
		for (int i = 2 * parent + 1; i < length - 1; i = i * 2 + 1) { // i为初始化为节点parent的左孩子，沿节点较大的子节点向下调整
			if ((i + 1) < length && array[i] < array[i + 1]) {
				i++; // 如果节点的右孩子>左孩子，则取右孩子节点的下标
			}
			if (temp >= array[i]) { // 根节点 >=左右子女中关键字较大者，调整结束
				break;
			} else { // 根节点 <左右子女中关键字较大者
				array[parent] = array[i]; // 将左右子结点中较大值array[i]调整到双亲节点上
				parent = i; // 【关键】修改k值，以便继续向下调整
			}
		}
		array[parent] = temp; // 被调整的结点的值放人最终位置
	}
}

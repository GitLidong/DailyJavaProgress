package com.lidong.suanfa.paixu.sort_demo;


/**
 * @author LiDong https://www.cnblogs.com/CherishFX/p/4643940.html
 */
// 堆排序是将数据看成是完全二叉树、根据完全二叉树的特性来进行排序的一种算法
// 最大堆要求节点的元素都要不小于其孩子，最小堆要求节点元素都不大于其左右孩子
// 那么处于最大堆的根节点的元素一定是这个堆中的最大值
//
// 完全二叉树有个特性：左边子节点位置 = 当前父节点的两倍 + 1，右边子节点位置 = 当前父节点的两倍 + 2

public class HeapSortTestDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Utils.print(array);

        HeapSortTestDemo demo = new HeapSortTestDemo();
        demo.heapSort(array);
        Utils.print(array);
    }

    // 构建大根堆：将array看成完全二叉树的顺序存储结构
    // 从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
    private int[] buildMaxHeap(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            adjudtDownToTop(array, i, array.length);
        }
        Utils.print(array);
        return array;
    }

    // 将元素array[k]自下往上逐步调整树形结构
    private void adjudtDownToTop(int[] array, int parent, int length) {
        int temp = array[parent];
        for (int i = 2 * parent + 1; i < length - 1; i = 2 * i + 1) {// i为初始化为节点parent的左孩子，沿节点较大的子节点向下调整
            if (i + 1 < length && array[i] < array[i + 1]) {// 取节点较大的子节点的下标
                i++;// 如果节点的右孩子>左孩子，则取右孩子节点的下标
            }
            if (temp > array[i]) { // 根节点 >=左右子女中关键字较大者，调整结束
                break;
            } else { // 根节点 <左右子女中关键字较大者
                array[parent] = array[i]; // 将左右子结点中较大值array[i]调整到双亲节点上
                parent = i; // 【关键】修改k值，以便继续向下调整
            }
        }
        array[parent] = temp; // 被调整的结点的值放人最终位置
    }

    // 堆排序
    public int[] heapSort(int[] array) {
        array = buildMaxHeap(array); // 初始建堆，array[0]为第一趟值最大的元素
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[0]; // 将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
            array[0] = array[i];
            array[i] = temp;
            adjudtDownToTop(array, 0, i); // 整理，将剩余的元素整理成堆
        }
        System.out.print("After heap Sort: ");
        return array;
    }


    // 删除堆顶元素操作
    public int[] deleteMax(int[] array) {
        // 将堆的最后一个元素与堆顶元素交换，堆底元素值设为-99999
        array[0] = array[array.length - 1];
        array[array.length - 1] = -99999;
        // 对此时的根节点进行向下调整
        adjudtDownToTop(array, 0, array.length);
        return array;
    }

    // 插入操作:向大根堆array中插入数据data
    public int[] insertData(int[] array, int data) {
        array[array.length - 1] = data; // 将新节点放在堆的末端
        int k = array.length - 1; // 需要调整的节点
        int parent = (k - 1) / 2; // 双亲节点
        while (parent >= 0 && data > array[parent]) {
            array[k] = array[parent]; // 双亲节点下调
            k = parent;
            if (parent != 0) {
                parent = (parent - 1) / 2; // 继续向上比较
            } else { // 根节点已调整完毕，跳出循环
                break;
            }
        }
        array[k] = data; // 将插入的结点放到正确的位置
        return array;
    }

}

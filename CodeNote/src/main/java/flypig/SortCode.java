package flypig;

import java.util.Arrays;

/**
 * @auther flypig
 * @date 2022/10/10
 * @package_name flypig
 * @project studyWork
 * @function: 实现各类排序的算法，包括优化实现，加油！！！！
 */
public class SortCode {
    public static void main(String[] args) {
        int[] A = {3,9,-1,10,20}; //0,1,2,3,4
        int[] B = {200,1,2,-1,-99,500,6};
        System.out.println(Arrays.toString(A));
        SortCode sc = new SortCode();
        //优化前的冒泡
//        sc.MaoPao1(A);
//        System.out.println(Arrays.toString(A));

        //小优化后的冒泡
//        sc.MaoPao2(A);
//        System.out.println(Arrays.toString(A));


        //选择排序SelectSort
        sc.SelectSort(A);
        System.out.println(Arrays.toString(A));

        sc.SelectSort(B);
        System.out.println(Arrays.toString(B));

    }

    //最基础的冒泡排序，j的条件是数组长度-i，因为最大的到最后了就不需要再比较了
    public void MaoPao1(int[] arr){
        int tag;                //临时变量，存储交换数据
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length-1; j++) {
                if(arr[j-1] > arr[j]){
                    tag = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tag;
                }

            }

        }
    }

    //优化：如果一次循环过程中一次交换都没做，说明已经有序了，就不需要再进行比较了，跳出最外层循环
    public void MaoPao2(int[] arr){
        int tag;                        //临时变量，存储交换数据
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length-1; j++) {
                if(arr[j-1] > arr[j]){
                    flag = true;
                    tag = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tag;
                }

            }

            if(!flag){
                break;
            }else{
                flag = false;
            }
        }
    }

    /*
    快速排序思想：
        先将需要排序的数组的第一个，记为标志位，将标记位和数组的最小值进行比较，如果标记位的数据不是最小则进行交换，将最小的
           数据交换到最前面，之后再将标志位进行移动，即可实现。
            主要点：
                标记位的移动
                求解标记位之后的数据的最小值
                将最小值进行交换
     */
    public void SelectSort(int[] arr){
        int len = arr.length;
        int flag = 0;               //记录除标记位外的最小值的数组下标
        int min;                    //记录最小值便于比较
        int temp;

        //第一层循环进行标志位的移动，第二层循环进行最小值的寻找
        for (int i = 0; i < len-1; i++) {
            min = arr[i+1];
//            System.out.println(min);
            for (int j = i+1; j < len-1 ; j++) {
                if(arr[j+1] < min){
                    min = arr[j+1];
                    flag = j+1;
                }
            }
//            System.out.println(flag);
            if(arr[i] > min){
                temp = arr[i];
                arr[i] = arr[flag];
                arr[flag] = temp;
            }

        }

    }



}

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
        int[] C = {8,9,1,7,2,3,5,4,6,0};
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

//        sc.SelectSort(B);
//        System.out.println(Arrays.toString(B));

        //插入排序
//        System.out.println(Arrays.toString(B));
//        sc.InsertionSort(B);
//        System.out.println(Arrays.toString(B));

        //希尔排序--优化版插入排序--交换版
        System.out.println(Arrays.toString(C));
        sc.shellSort(C);
        System.out.println(Arrays.toString(C));

        //希尔排序--优化版插入排序--移位版
        System.out.println(Arrays.toString(B));
        sc.shellSort2(B);
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

    /*
       插入排序：
       基本思想：将待排序数组分成两部分：一部分已排好序、初始为第一个元素，一部分未排序，将未排序好的数据一个个取出，
                取出后往已排好序的部分进行比较插入排序即可
       基本步骤：
                1.从第一个元素开始，将第一个元素看作是已经排好的数。
                2.取出下一个元素，然后与前面排好的元素相比（从后向前依次比较）。
                3.直到这个元素遇到比他小的元素。
                4.将新元素插入到该元素的后面。
                5.重复以上步骤。
     */

    public void InsertionSort(int[] arr){
        int insertVal;
        int insertIndex;

        //循环的从未排序好的数组中取值，所以i的初始值为1，进行循环
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i-1;  //给待插入的位置附上初始值
            insertVal = arr[i]; //将插入值赋值

            //循环的将待插入值与排序好的数组比较，已排好序的数组中arr[insertIndex]一直都是最大值，
//            进去后插入后index要减一，避免数组越界
            while(insertIndex >=0 && insertVal < arr[insertIndex]){
                arr[insertIndex +1] = arr[insertIndex];
                insertIndex--;
            }
            //insertIndex+1就是插入位置
            //如果满足条件，证明这次的insertVal是大于待插入的值的，所以不用交换位置，不用插入值
            if(insertIndex +1 != i){
                arr[insertIndex+1] = insertVal;
            }

        }

    }


    /*
    希尔排序
    基本思路：是插入排序的改良版本、设定初始增量gap=length/2，表示第一轮：数组[0]与数组[0+gap]进行交换，
        第二轮再缩小增量gap=(length/2)/2,数组[0]与数组[0+gap]进行交换，把小的换到前面
        最后的增量变成了1，进行数组[0]与数组[0+1]的比较，循环比较。
        最后一轮的比较是以插入排序进行排序，比较的效率会提高

    基本步骤：
        在有序序列中插入采用交换法：
            第一个循环for(int gap = arr.length/2;gap>=0;gap/=2){
            第二个循环for(int i=gap;i<arr.length;i++)
            第三个循环for(int j=i-gap;j>=0;j-=gap)
     */
    public void shellSort(int[] arr){
        int temp;
        for(int gap = arr.length/2;gap>0;gap/=2){
            for(int i = gap;i<arr.length;i++){
                for(int j = i-gap;j>=0;j-=gap){

                    if(arr[j]>arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    //对交换式的希尔排序进行优化-》移位法
    public void shellSort2(int[] arr){
        //增量gap,并逐步的缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0&&temp<arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }

                    //当退出while则找到了插入位置
                    arr[j] = temp;
                }
            }
        }


    }

}

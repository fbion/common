package Algorithms;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：CircleMatrix</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/29 17:27<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/29 17:27<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class CircleMatrix {

    /**
     * <p>方法描述: 按指定起始值，和大小输出回形矩阵</p>
     *
     * <p>方法备注: </p>
     * @param start
     * @param size
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/5/12 9:33</p>
     */
    public static void printMatrix(int start, int size) {
        int[][] array = new int[size][size];    //保存矩阵值
        int xIndex = 0;
        int yIndex = 0;
        int xBorderMax = size;
        int xBorderMin = 0;
        int yBorderMax = size;
        int yBorderMin = 0;
        boolean flag = true;
        boolean xFlag = true;
        boolean yFlag = true;
        for (int i = 0; i < size * size; i++) {
            array[yIndex][xIndex] = start + i;
            if(flag) {
                if(xFlag) {
                    if(xIndex >= xBorderMax - 1) {
                        flag = false;
                        xFlag = false;
                        xBorderMax--;
                        yIndex++;
                    } else {
                        xIndex++;
                    }
                }else {
                    if(xIndex <= xBorderMin) {
                        flag = false;
                        xFlag = true;
                        xBorderMin++;
                        yIndex--;
                    } else {
                        xIndex--;
                    }
                }
            } else {
                if(yFlag) {
                    if(yIndex >= yBorderMax - 1) {
                        flag = true;
                        yFlag = false;
                        yBorderMax--;
                        xIndex--;
                    } else {
                        yIndex++;
                    }
                }else {
                    if(yIndex <= yBorderMin + 1) {
                        flag = true;
                        yFlag = true;
                        yBorderMin++;
                        xIndex++;
                    } else {
                        yIndex--;
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {            //打印输出
            for (int j = 0; j < size; j++) {
                System.out.print(array[i][j]);
                System.out.print("\t\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printMatrix(10, 7);
    }
}

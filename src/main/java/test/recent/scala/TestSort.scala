package test.recent.scala

import  scala.util.Random
import scala.collection.mutable.ArrayBuffer
;
/**
  * Created by Administrator on 2015/12/29.
  */
object TestSort {

    def sort(array:Array[Int]) ={
        for(i <- 1 until array.length) {
            var temp = array(i)
            var j = i
            while (j > 0 && temp < array(j - 1)){
                array(j) = array(j - 1)
                j -= 1;
            }
            array(j) = temp
        }
    }

    def createRandomArray(size: Int) = {
        val rand =new Random()
        (0 until 9).map( i =>
            rand.nextInt(100)
        ).toArray
    }

    def main(args: Array[String]): Unit ={
        val a = createRandomArray(9)
        println(a.mkString("\t"))
        println()
        sort(a)
        println(a.mkString("\t"))

        println()
        val b = Array(3,5,9,1,6)
//        val c = b.sortWith(_ < _)
//        val c = b.sorted
        scala.util.Sorting.quickSort(b)
        for(i <- b) print(i + "\t");
    }
}

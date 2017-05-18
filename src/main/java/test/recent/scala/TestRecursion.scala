package test.recent.scala

/**
  * Created by Administrator on 2015/12/8.
  */
object TestRecursion {

    def sum(n: Long): Long = {
      if (n <= 0) n
      else  n + sum(n - 1)
    }

  def main(args: Array[String]) {
      val total = sum(10000000)
      println(total)
//    for(i <- 1 to 3; j <- 1 to 3)
//    {
//      print(10 * i + j + "");
//    }
  }
}

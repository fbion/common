package test.recent.scala

/**
  * Created by Administrator on 2016/1/12.
  */
object Test {

  def sum(lst: List[Int]): Int = if(lst == Nil) 0 else lst.head + sum(lst.tail)

  def sum1(lst: List[Int]): Int = lst match {
    case Nil => 0
    case h::t => h + sum(t)
  }

  def main(args: Array[String]): Unit = {
//    var lst = List(1,3,5,6,8)
//    var lst = 1::3::5::6::8::Nil
//    print(lst.sum)

    var l =List(-2,+9).collect{case '+' => 1;case '-' => -1}
    print(l)
  }
}

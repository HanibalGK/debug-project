package com.project.debug.base

/**
  * 2019/11/15
  * com.project.debug.base
  *
  * @author jiaofanghao
  *
  **/
object CollectionDemo {

  def main(args: Array[String]): Unit = {

  }

  /**
    * array 类型参数化数组
    *     数组访问要用 "(i)"
    * 与 Java 不同的原因:
    *     因为 Scala 中, 对象 / 类 后添加 "()"
    *       -> array(index)       -> array.apply(index)
    *       -> array(index) = obj -> array(index).update(obj)
    */
  def arrayDemo(): Unit = {
    // 1. 定义数组
    val greetStrings = new Array[String](3)
    // 2. 为数组赋值
    greetStrings(0) = "Hello"
    greetStrings(1) = ","
    greetStrings(2) = "world!\n"

    // 3. 循环打印 array 中的数据
    for (i <- 0 to 2)
      print(greetStrings(i))
  }

  /**
    * 列表:
    *     降低耦合度, 参数和返回值都经过类型检查器检查
    *  不可变同类对象序列    immutable
    *  可变同类对象序列      mmutable
    */
  def listDemo(): Unit = {

  }

}

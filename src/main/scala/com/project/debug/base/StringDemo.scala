package com.project.debug.base

/**
  * 2019/11/14
  * com.project.debug.base
  *
  * @author jiaofanghao
  *   Base Function & Base Use
  **/
object StringDemo {

  def main(args: Array[String]): Unit = {
    collectionDefine()
  }

  def fieldDefine(): Unit = {
    // 定义变量
    val name: String = "张三"
    var age:  Int    = 17

    // 名称是不可更改的, 因为名称为 val 定义
    // val -> final
    // name = "李四"

    // var 定义的类型为可变的, 可以随时进行赋值, 但是在定义的时候需要指定变量类型
    age = 18
  }

  /**
    * 字符串定义
    */
  def strFieldDefine(): Unit = {
    // 多行字符定义
    val mutiLine =
      """
        | This is a mutiLine String,
        | mutiLine String print also is mutiLine.
      """.stripMargin
    println(mutiLine)

    // 字符串变量拼接
    val name = "ZhangSan"
    val age  = 14
    val variableStr = s"My Name is ${name} and my age is ${age}"

    println(variableStr)
  }

  /**
    * 方法定义
    * def 方法名 (形参1: 形参类型 [,形参2: 形参2类型,...]): 返回值类型 = {
    *     statement...
    *     // Scala 可以将最后的变量当作返回值进行返回, 不需要添加 return
    *     result_element
    * }
    * @param x
    * @param y
    * @return
    */
  def functionDefine(x: Int, y: Int): Int = {
    if (x > y)
      x
    else
      y
  }

  /**
    * 集合定义
    */
  def collectionDefine(): Unit = {
    // 1. List 定义
    // 创建实际上使用了 List 的伴生对象的 apply 方法
    var list  = List("01", "02", "03")
    var list2 = List.apply("01", "02", "03")

    println(list)

    // List 添加
    // "::" -> cons
    // 将新元素组合到现有列表的最前端, 然后返回作为执行结果的新列表
    // 语法糖调用规则为:
    //    一般调用的方法为左操作数 -> a * b == a.*(b)
    //    添加 : 则为右操作数调用  -> a *: b == b.*(a)
    val newList  = "3" :: list
    val newList2 = list2.::("3")

    // 2. List 组合
    val oneTwo = List(1, 2)
    val twoThree = List(2,3)

    val oneTwoTowThree = oneTwo ::: twoThree
  }

}

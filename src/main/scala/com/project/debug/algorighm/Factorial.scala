package com.project.debug.algorighm

import java.math.BigInteger

/**
  * 2019/11/14
  * com.project.debug.algorighm
  *
  * @author jiaofanghao
  *
  **/
object Factorial {

  /**
    * 斐波那契函数 - Scala 自带数据类型
    * @param x
    * @return
    */
  def factorial(x: BigInt): BigInt = {
    if (x == 0) 1 else x * factorial(x - 1)
  }

  /**
    * 斐波那契函数 - Java BigInteger 类型
    * @param x
    * @return
    */
  def factorial(x: BigInteger): BigInteger = {
    if ( x == BigInteger.ZERO ) {
      BigInteger.ONE
    } else {
      x.multiply(factorial(x.subtract(BigInteger.ONE)))
    }
  }

/*  def main(args: Array[String]): Unit = {
    val result = factorial(10)
    print(result)
  }*/

}

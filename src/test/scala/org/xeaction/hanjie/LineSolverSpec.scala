package org.xeaction.hanjie

import org.scalatest._
import org.scalatest.matchers.BeMatcher

class LineSolverSpec extends WordSpec with Matchers {
  val cellCount = 10
  
  val invalid = List(
      Line(11),
      Line(5,5)
    )
  val validSingle = List(
      Line(5,4),
      Line(10)
    )
  val validMultiple = List(
      Line(1,1,1,1,1)
    )
    
  def isValidCombination(combination: Seq[Boolean]) {
    
  }
  
  "LineSolver" when {
    "creating combinations" should {
      "return 1 result for valid complete line" in {
        validSingle foreach { line =>
          val ls = LineSolver(line, cellCount)
          val combinations = ls.combinations
          combinations.length should be (1)
          val lineSum = line.numbers.sum
          val combination = combinations(0)
          combination.filter(_ == true).length should be (lineSum)
        }
      }
      "return correct combinations for valid lines" in {
        validMultiple foreach { line =>
          val ls = LineSolver(line, cellCount)
          ls.combinations.length should be > (0)
          ls.combinations.foreach(_.length should be (cellCount))
        }
      }
    }
    "instantiating" should {
      "throw IllegalStateException if numbers add up to more than line length" in {
        invalid foreach { line =>
          an [IllegalStateException] should be thrownBy {
            val ls = LineSolver(line, cellCount)
          }
        }
      }
    }
    "checking valid combinations" should {
      val ls = LineSolver(Line(5,4), 10)
      val i = Cell(Some(true))
      val o = Cell(Some(false))
      val x = Cell(None)
      "return true if all cells are empty" in {
        val cells = Seq.fill(10)(x)
        val result = ls.isValidCombination(cells, Seq(true, false, true, false, true, true, false, true, false, true))
        result should be (true)
      }
      "return true for valid combination" in {
        val cells = Seq(i, o, i, o, i, i, o, i, o, i)
        val result = ls.isValidCombination(cells, Seq(true, false, true, false, true, true, false, true, false, true))
        result should be (true)
      }
      "return false for invalid combination" in {
        val cells = Seq.fill(10)(i)
        val result = ls.isValidCombination(cells, Seq(true, false, true, false, true, true, false, true, false, true))
        result should be (false)
      }
    }
  }
}
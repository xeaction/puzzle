package org.xeaction.hanjie

import org.scalatest._

class LineSolverSpec extends WordSpec with Matchers {
  val ls = LineSolver(10)
  
  val invalid = List(
      Line(11),
      Line(5,5)
    )
  val validSingle = List(
      Line(5,4),
      Line(10)
    )
    
  def isValidCombination(combination: Seq[Boolean]) {
    
  }
  
  "LineSolver" when {
    "creating combinations" should {
      "return List() if combination is invalid" in {
        invalid foreach {
          ls.combinations(_) should be (List())
        }
      }
      "return 1 result for valid complete line" in {
        validSingle foreach { line =>
          val combinations = ls.combinations(line)
          combinations.length should be (1)
          val lineSum = line.numbers.sum
          val combination = combinations(0)
          combination.filter(_ == true).length should be (lineSum)
        }
      }
    }
  }
}
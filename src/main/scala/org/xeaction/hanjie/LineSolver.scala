package org.xeaction.hanjie

import scala.annotation.tailrec
import scala.collection._

class LineSolver(line: Line, cellCount: Int) {
  // check numbers don't add up to more than cell count
  val numberSum = line.numbers.sum + line.numbers.length - 1
  if (numberSum > cellCount) {
    throw new IllegalStateException("Numbers are longer than number of cells")
  }
  
  
  def solve(cells: Seq[Cell]): Seq[Cell] = {
    val vcs = validCombinations(cells)
    (vcs.head.map(Option(_)) /: vcs) { (c, combination) =>
      c zip combination map { case (c1, c2) =>
        c1.filter(_ == c2)
      }
    }.map(Cell(_))
  }

  def validCombinations(cells: Seq[Cell]) = combinations.filter(isValidCombination(cells, _))
  
  def isValidCombination(cells: Seq[Cell], combination: Seq[Boolean]): Boolean = {
    cells zip combination forall { case (cell, result) =>
      cell.value.map(_ == result).getOrElse(true)
    }
  }
  
  lazy val combinations: Seq[Seq[Boolean]] = {
    val filteredCombinations = combinationRecur(line.numbers, cellCount, 0).filterNot(_ == None).map(_.get)
    filteredCombinations
  }
  
  def combinationRecur(numbers: Seq[Int], cellCount: Int, emptyPrefix: Int): Seq[Option[Seq[Boolean]]] = numbers match {
    case Nil => {
      Seq(Some(Seq.fill(cellCount)(false)))
    }
    case number :: tail if number + emptyPrefix > cellCount => Seq(None)
    case number :: tail => {
      val prefix = Seq.fill(emptyPrefix)(false)
      val remainingCellCount = cellCount - number - emptyPrefix - 1
      val filledCells = prefix ++ Seq.fill(number)(true)
      val filledCellsWithSpace = if (number + emptyPrefix == cellCount) filledCells else filledCells :+ false
      combinationRecur(numbers, cellCount, emptyPrefix + 1) ++
      combinationRecur(tail, remainingCellCount, 0).map { combOpt =>
        combOpt.map { comb =>
          filledCellsWithSpace ++ comb
        }
      }
    }
  }
}

object LineSolver {
  def apply(line: Line, cellCount: Int) = new LineSolver(line, cellCount)
}
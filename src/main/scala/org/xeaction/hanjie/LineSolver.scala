package org.xeaction.hanjie

import scala.annotation.tailrec

class LineSolver(cellCount: Int) {
  def solve(line: Line) = {
    // check numbers don't add up to more than cell count
    val numberSum = line.numbers.sum + line.numbers.length - 1
    if (numberSum > cellCount) {
      throw new IllegalStateException("Numbers are longer than number of cells")
    }
    
    
  }
  
  def combinations(line: Line): Seq[Seq[Boolean]] = {
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
  def apply(cellCount: Int) = new LineSolver(cellCount)
}
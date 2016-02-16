

package org.xeaction.hanjie

case class Cell(value: Option[Boolean])

class Line(val numbers: Seq[Int])

object Line {
  def apply(numbers: Int*) = new Line(numbers.toList)
}

case class LineSolution(line: Line)

case class Grid(cols: Seq[Line], rows: Seq[Line], cells: Seq[Cell])




package org.xeaction.hanjie

case class Cell(value: Option[Boolean], column: Line, row: Line)

case class Line(numbers: Int*)

case class Grid(cols: Seq[Line], rows: Seq[Line], cells: Seq[Cell])


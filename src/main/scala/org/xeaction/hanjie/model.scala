

package org.xeaction.hanjie

case class Cell(value: Option[Boolean])

case class Line(numbers: Seq[Int], cells: Seq[Cell])

case class Grid(cols: Seq[Line], rows: Seq[Line], cells: Seq[Cell])


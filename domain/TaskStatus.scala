package domain

sealed abstract class TaskStatus(val value: String)

object TaskStatus {
  case object BeforeStart extends TaskStatus("BeforeStart")
  case object InProgress  extends TaskStatus("InProgress")
  case object Done extends TaskStatus("Done")

  def apply(value: String): TaskStatus = {
    value match {
      case "BeforeStart" => BeforeStart
      case "InProgress" => InProgress
      case "Done" => Done
    }
  }
}
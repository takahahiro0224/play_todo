package domain

import java.util.UUID

case class Task(id: String, title: Title, status: TaskStatus)

object Task {
  def apply(titleString: String,
            statusString: String): Task = {
    val uuid = UUID.rondamUUID().toString
    new Task(
      id = uuid,
      title = Title(tittleString),
      status = TaskStatus(statusString)
    )
  }

  def apply(id: String,
            statusString: String,
            taskString): Task = {
    new Task(
      id = id,
      title = Title(titleString)
      statusString = TaskStatus(statusString)
    )
  }
}
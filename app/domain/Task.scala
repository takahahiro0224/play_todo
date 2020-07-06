package domain

import java.util.UUID

case class Task(id: String, title: Title, status: TaskStatus)

object Task {
  def apply(titleString: String,
            statusString: String): Task = {
    val uuid = UUID.randomUUID().toString
    new Task(
      id = uuid,
      title = Title(titleString),
      status = TaskStatus(statusString)
    )
  }

  def apply(id: String,
            titleString: String,
            statusString: String): Task = {
    new Task(
      id = id,
      title = Title(titleString),
      status = TaskStatus(statusString)
    )
  }
}
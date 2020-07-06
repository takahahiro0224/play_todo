package controllers.dtos

import domain.Task

case class TaskDTO(id: String,
                   title: String,
                   status: String)

object TaskDTO {

  def apply(task: Task): TaskDTO = {
    new TaskDTO(
      task.id,
      task.title.value,
      task.status.value
    )
  }
}
package infra.records

import domain.Task
import scalikejdbc.WrappedResultSet
import scalikejdbc._

case class TaskRecord(
                     id: String,
                     title: String,
                     status: String
                     )

object TaskRecord extends SQLSyntaxSupport[TaskRecord] {
  def apply(task: Task): TaskRecord = new TaskRecord(
    task.id,
    task.title.value,
    task.status.value
  )

  def apply(rn: ResultName[TaskRecord])(rs: WrappedResultSet): TaskRecord = {
    new TaskRecord(
      rs.string("id"),
      rs.string("title"),
      rs.string("status")
    )
  }
}

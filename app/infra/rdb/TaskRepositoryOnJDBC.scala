package infra.rdb

import domain.{Task, TaskStatus, Title}
import domain.repositories.TaskRepository
import infra.records.TaskRecord
import scalikejdbc.{DB, _}

import scala.util.Try
class TaskRepositoryOnJDBC extends TaskRepository {

  val b: QuerySQLSyntaxProvider[SQLSyntaxSupport[TaskRecord], TaskRecord] = TaskRecord.syntax("b")

  def findAll(): Try[Seq[Task]] = Try {
    DB readOnly { implicit session =>
      sql"select * from task"
        .map(TaskRecord(b.resultName))
        .list()
        .apply()
        .map(toModel)
    }
  }

  def add(task: Task): Try[Unit] = Try {
    val record = TaskRecord(task)
    DB localTx { implicit session =>
      sql"""insert into task (id, title, status)
            |values (
            |${record.id},
            |${record.title},
            |${record.status}
            |)
         """.stripMargin
        .update()
        .apply()
    }
  }

  private val toModel: TaskRecord => Task = { taskRecord =>
    new Task(
      taskRecord.id,
      Title(taskRecord.title),
      TaskStatus(taskRecord.status)
    )
  }

}

package domain.repositories

import domain.Task
import scala.util.Try

trait TaskRepository {

  def findAll(): Try[Seq[Task]]

  def add(task: Task): Try[Unit]

}

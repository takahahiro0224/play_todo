package controllers

import controllers.dtos.TaskDTO
import controllers.forms.TaskCreate
import domain.Task
import domain.repositories.TaskRepository
import javax.inject.{Inject, Singleton}
import play.api.Logging
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.util.{Failure, Success}

@Singleton
class TaskController @Inject()(cc: ControllerComponents, taskRepository: TaskRepository)
  extends AbstractController(cc) with I18nSupport with Logging {

  def index(): Action[AnyContent] = Action { implicit request =>
    taskRepository.findAll() match {
      case Success(tasks) => {
        val taskDTOs = tasks.map(TaskDTO(_))
        Ok(views.html.task.index(taskDTOs))
      }
      case Failure(ex) => {
        logger.error(s"index occured error", ex)
        InternalServerError(ex.getMessage)

      }
    }
  }

  def createForm(): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.task.createForm(TaskCreate.form))
  }

  def create() = Action { implicit request =>
    TaskCreate.form.bindFromRequest.fold(
      error => BadRequest(views.html.task.createForm(error)),
      task => {
        (for {
          result <- taskRepository.add(Task(task.title, task.status))
        } yield result) match {
          case Success(_) => Redirect("/")
          case Failure(ex) => {
            logger.error(s"occured error", ex)
            InternalServerError(ex.getMessage)
          }

        }
      }
    )
  }


}

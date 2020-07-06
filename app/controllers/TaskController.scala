package controllers

import controllers.dtos.TaskDTO
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


}

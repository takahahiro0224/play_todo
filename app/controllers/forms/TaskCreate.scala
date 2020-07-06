package controllers.forms
import play.api.data.Form
import play.api.data.Forms.{email, mapping, nonEmptyText}
import play.api.data.validation._


case class TaskCreate(title: String,
                      status: String)

object TaskCreate {
  val form: Form[TaskCreate] = Form(
    mapping(
      "title" -> nonEmptyText,
      "status" -> nonEmptyText
    )(TaskCreate.apply)(TaskCreate.unapply)
  )
}


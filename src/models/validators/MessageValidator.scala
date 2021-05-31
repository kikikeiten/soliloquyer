package models.validators

import java.util
import models.Message

import java.util.{ArrayList, List}


object MessageValidator {
  def validate(m: Message): util.List[String] = {
    val errors = new util.ArrayList[String]
    val title_error = _validateTitle(m.getTitle)
    if (!(title_error == "")) errors.add(title_error)
    val content_error = _validateContent(m.getContent)
    if (!(content_error == "")) errors.add(content_error)
    errors
  }

  private def _validateTitle(title: String): String = {
    if (title == null || title == "") return "タイトルを入力してください。"
    ""
  }

  private def _validateContent(content: String): String = {
    if (content == null || content == "") return "メッセージを入力してください。"
    ""
  }
}

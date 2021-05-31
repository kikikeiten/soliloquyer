package models

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table


@Entity
@NamedQueries(Array(new NamedQuery(name = "getAllMessages", query = "SELECT m FROM Message AS m ORDER BY m.id DESC"), new NamedQuery(name = "getMessagesCount", query = "SELECT COUNT(m) FROM Message AS m")))
@Table(name = "messages") class Message {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) private var id = null
  @Column(name = "title", length = 255, nullable = false) private var title = null
  @Column(name = "content", length = 255, nullable = false) private var content = null
  @Column(name = "created_at", nullable = false) private var created_at = null
  @Column(name = "updated_at", nullable = false) private var updated_at = null

  def getId: Integer = id

  def setId(id: Integer): Unit = {
    this.id = id
  }

  def getTitle: String = title

  def setTitle(title: String): Unit = {
    this.title = title
  }

  def getContent: String = content

  def setContent(content: String): Unit = {
    this.content = content
  }

  def getCreated_at: Timestamp = created_at

  def setCreated_at(created_at: Timestamp): Unit = {
    this.created_at = created_at
  }

  def getUpdated_at: Timestamp = updated_at

  def setUpdated_at(updated_at: Timestamp): Unit = {
    this.updated_at = updated_at
  }
}

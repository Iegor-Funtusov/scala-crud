package storage

import entity.Student

trait Storage {
  def create(student: Student): Unit
  def update(student: Student): Unit
  def delete(id: String): Unit
  def findAll(): List[Student]
  def findById(id: String): Option[Student]
}

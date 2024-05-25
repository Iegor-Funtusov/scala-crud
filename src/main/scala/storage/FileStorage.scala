package storage

import entity.Student
import factory.StorageFactory

import java.io.File
import java.util.UUID
import scala.annotation.tailrec

trait FileStorage extends Storage {

  protected var students: List[Student] = List()
  protected var STUDENT_FILE: String = "students."

  {
    val format = StorageFactory.getFormat
    STUDENT_FILE = STUDENT_FILE + format
    val storage = File(STUDENT_FILE)
    if !storage.exists() then storage.createNewFile()
  }

  override def create(student: Student): Unit =
    readStudentsFromFile()
    students = Student(
      generateId(),
      student.firstName, student.lastName, student.age
    ) +: students
    writeStudentsToFile()

  override def update(student: Student): Unit =
    readStudentsFromFile()
    students = students.updated(
      students.indexWhere(element => element.id == student.id),
      student
    )
    writeStudentsToFile()

  override def delete(id: String): Unit =
    readStudentsFromFile()
    students = students.filterNot(student => student.id == id)
    writeStudentsToFile()

  override def findAll(): List[Student] = {
    readStudentsFromFile()
    students
  }

  override def findById(id: String): Option[Student] =
    readStudentsFromFile()
    students.find(student => student.id == id)

  def readStudentsFromFile(): Unit

  def writeStudentsToFile(): Unit

  @tailrec
  private def generateId(): String =
    val id = UUID.randomUUID().toString
    if (students.exists(s => s.id == id)) generateId() else id
}

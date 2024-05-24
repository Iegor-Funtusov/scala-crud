package dao

import bd.{BDStorage, CSVStorage}
import entity.Student

class StudentDaoImpl extends StudentDao {

  private var students: List[Student] = List()
  private val storage: BDStorage = CSVStorage()

  override def create(student: Student): Unit = storage.create(student)

  override def update(student: Student): Unit = storage.update(student)

  override def delete(id: String): Unit = storage.delete(id)

  override def findAll(): List[Student] = storage.findAll()

  override def findById(id: String): Option[Student] = storage.findById(id)
}

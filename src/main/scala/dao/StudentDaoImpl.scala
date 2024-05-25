package dao

import entity.Student
import factory.StorageFactory
import storage.Storage

class StudentDaoImpl extends StudentDao {

  private val storage: Storage = StorageFactory.getStorage

  override def create(student: Student): Unit = storage.create(student)

  override def update(student: Student): Unit = storage.update(student)

  override def delete(id: String): Unit = storage.delete(id)

  override def findAll(): List[Student] = storage.findAll()

  override def findById(id: String): Option[Student] = storage.findById(id)
}

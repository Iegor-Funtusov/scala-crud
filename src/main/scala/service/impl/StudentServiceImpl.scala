package service.impl

import dao.StudentDaoImpl
import entity.Student
import service.StudentService

class StudentServiceImpl extends StudentService {

  private val studentDao: StudentDaoImpl = new StudentDaoImpl()

  override def create(entity: Student): Unit = studentDao.create(entity)

  override def update(entity: Student): Unit = studentDao.update(entity)

  override def delete(id: String): Unit = studentDao.delete(id)

  override def findAll(): List[Student] = studentDao.findAll()

  override def findById(id: String): Student = studentDao.findById(id).get
}

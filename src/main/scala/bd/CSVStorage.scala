package bd

import com.opencsv.{CSVReader, CSVWriter}
import com.opencsv.exceptions.CsvException
import entity.Student

import java.io.{FileReader, FileWriter, IOException}
import java.util.UUID
import scala.annotation.tailrec
import scala.jdk.CollectionConverters.*

class CSVStorage extends BDStorage {

  private var students: List[Student] = List()
  private val STUDENT_CSV: String = "students.csv"

  override def create(student: Student): Unit =
    readCSV()
    students = Student(
      generateId(),
      student.firstName, student.lastName, student.age
    ) +: students
    writeCSV()

  override def update(student: Student): Unit =
    readCSV()
    students = students.updated(
      students.indexWhere(element => element.id == student.id),
      student
    )
    writeCSV()

  override def delete(id: String): Unit =
    readCSV()
    students = students.filterNot(student => student.id == id)
    writeCSV()

  override def findAll(): List[Student] = {
    readCSV()
    students
  }

  override def findById(id: String): Option[Student] =
    readCSV()
    students.find(student => student.id == id)

  @tailrec
  private def generateId(): String =
    val id = UUID.randomUUID().toString
    if (students.exists(s => s.id == id)) generateId() else id

  private def readCSV(): Unit = {
    try {
      val reader: CSVReader = CSVReader(FileReader(STUDENT_CSV))
      students = List()
      val list = reader.readAll()
      list.asScala.toList.foreach(el =>
        students = Student(el.apply(0), el.apply(1), el.apply(2), el.apply(3).toInt) +: students)
      reader.close()
    } catch {
      case e: CsvException => println("Couldn't read that file")
      case e: IOException => println("Couldn't find that file")
    }
  }

  private def writeCSV(): Unit = {
    try {
      val writer: CSVWriter = CSVWriter(FileWriter(STUDENT_CSV))
      var list: List[Array[String]] = List()
      students.foreach(student => {
        val str = Array(student.id, student.firstName, student.lastName, student.age.toString)
        list = str +: list
      })
      writer.writeAll(list.asJava)
      writer.close()
    } catch {
      case e: CsvException => println("Couldn't read that file")
      case e: IOException => println("Couldn't find that file")
    }
  }
}

package storage

import com.opencsv.{CSVReader, CSVWriter}
import com.opencsv.exceptions.CsvException
import entity.Student

import java.io.{File, FileReader, FileWriter, IOException}
import scala.jdk.CollectionConverters.*

class CSVStorage extends FileStorage {

  private val STUDENT_CSV: String = "students.csv"
  private val fileStorage: File = File(STUDENT_CSV)

  {
    if !fileStorage.exists() then fileStorage.createNewFile()
  }

  override def readStudentsFromFile(): Unit = {
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

  override def writeStudentsToFile(): Unit = {
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

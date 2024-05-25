package storage

import entity.Student
import io.circe.*
import io.circe.parser.*
import io.circe.syntax.*
import io.circe.generic.auto.{deriveDecoder, deriveEncoder}
import cats.syntax.either.*

import java.io.{File, FileWriter, IOException}

class JsonStorage extends FileStorage {

  override def readStudentsFromFile(): Unit = {
    val jsonString: String = os.read(os.pwd / STUDENT_FILE)
    if (jsonString.nonEmpty) {
      val parseResult: Either[ParsingFailure, Json] = parse(jsonString)
      val result = processJson(parseResult)
      result match
        case Left(failure) => println(failure)
        case Right(json) => students = json.students
    }
  }

  override def writeStudentsToFile(): Unit = {
    val json: Json = StudentData(students).asJson
    try {
      val writer = FileWriter(File(STUDENT_FILE))
      writer.write(json.toString)
      writer.close()
    } catch
      case e: IOException => println(e)
  }

  private def processJson(json: Either[ParsingFailure, Json]): Either[Error, StudentData] =
    json
      .leftMap(err => err: Error)
      .flatMap(_.as[StudentData])
}

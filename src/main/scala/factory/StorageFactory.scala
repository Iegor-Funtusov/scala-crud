package factory

import factory.yaml.FileFormat
import cats.syntax.either.*
import io.circe.*
import io.circe.generic.auto.*
import io.circe.yaml
import storage.{CSVStorage, JsonStorage, Storage}

import java.io.FileReader
import scala.util.Try

object StorageFactory {

  private var format: String = ""
  private var storage: Option[Storage] = None

  private val yamlFileReader: Either[Throwable, FileReader] =
    Try {
      new FileReader(this.getClass.getClassLoader.getResource("app.yaml").getFile)
    }.toEither

  private val appFileConfig: Either[Throwable, FileFormat] =
    yamlFileReader
      .map(fileReader => processJson(yaml.parser.parse(fileReader)))
      .flatten

  private def processJson(json: Either[ParsingFailure, Json]): Either[Error, FileFormat] =
    json
      .leftMap(err => err: Error)
      .flatMap(_.as[FileFormat])

  private def init(): Unit =
    val format: FileFormat = appFileConfig.getOrElse(FileFormat(""))
    this.format = format.fileFormat

  def getFormat: String = this.format

  def getStorage: Storage = {
    init()
    if storage.isEmpty then format match
      case "csv" => storage = Option.apply(CSVStorage())
      case "json" => storage = Option.apply(JsonStorage())
    storage.get
  }
}

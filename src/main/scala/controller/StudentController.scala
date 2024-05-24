package controller

import entity.Student
import service.StudentService
import service.impl.StudentServiceImpl

import scala.io.StdIn.readLine
import scala.io.StdIn.readInt

class StudentController {

  private val studentService: StudentService = StudentServiceImpl()

  def start(): Unit = {
    println("Welcome to app")
    menu()
    var command: String = ""
    while (command != "exit") {
      command = readLine()
      goToOperations(command)
      menu()
    }
  }

  private def menu(): Unit = {
    println("If you want create student please enter 1")
    println("If you want find all student please enter 2")
    println("If you want find by id student please enter 3")
    println("If you want update student please enter 4")
    println("If you want delete student please enter 5")
    println("If you want exit please enter exit")
  }

  private def goToOperations(command: String): Unit = {
    command match
      case "1" => create()
      case "2" => findAll()
      case "3" => findById()
      case "4" => update()
      case "5" => delete()
      case _ =>
  }

  private def create(): Unit = {
    println("Please enter first name")
    val fName: String = readLine()
    println("Please enter last name")
    val lName: String = readLine()
    println("Please enter age")
    val age: Int = readInt()

    val student = Student(null, fName, lName, age)

    studentService.create(student)
  }

  private def update(): Unit = {
    println("Please enter id")
    val id: String = readLine()
    println("Please enter first name")
    val fName: String = readLine()
    println("Please enter last name")
    val lName: String = readLine()
    println("Please enter age")
    val age: Int = readInt()

    val student = Student(id, fName, lName, age)

    studentService.update(student)
  }

  private def delete(): Unit = {
    println("Please enter id")
    val id: String = readLine()
    studentService.delete(id)
  }

  private def findAll(): Unit = studentService.findAll().foreach(elem => println(elem))

  private def findById(): Unit = {
    println("Please enter id")
    val id: String = readLine()
    val student = studentService.findById(id)
    println(s"Current student: $student")
  }
}

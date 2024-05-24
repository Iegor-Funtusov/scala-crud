package service

trait CrudService[E] {
  def create(entity: E): Unit
  def update(entity: E): Unit
  def delete(id: String): Unit
  def findAll(): List[E]
  def findById(id: String): E
}

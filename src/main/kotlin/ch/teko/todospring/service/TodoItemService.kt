package ch.teko.todospring.service

import ch.teko.todospring.controller.ui.dto.TodoItemDto
import ch.teko.todospring.controller.ui.dto.ListFilter
import ch.teko.todospring.controller.ui.dto.ListFilter.*
import ch.teko.todospring.db.TodoItem
import ch.teko.todospring.db.repository.TodoItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoItemService {

    @Autowired
    lateinit var repository: TodoItemRepository

    fun getNumberOfCompletedItems(): Int {
        return repository.countAllByCompleted(true)
    }

    fun getTodoItems(): List<TodoItemDto> {
        return repository.findAll().map {
            TodoItemDto(it.id, it.title, it.completed)
        }
    }

    fun getNumberOfActiveItems(): Int {
        return repository.countAllByCompleted(false)
    }

    fun count(): Long {
        return repository.count()
    }

    fun deleteAllCompletedItems() {
        val items = repository.findAllByCompleted(true)
        for (item in items) {
            repository.deleteById(item.id!!)
        }
    }

    fun toggleSelectedItem(id: Long) {
        repository.findById(id).ifPresent {
            it.completed = !it.completed
            repository.save(it)
        }
    }

    fun toggleAll() {
        val todoItems = repository.findAll()
        for (todoItem in todoItems) {
            todoItem.completed = !todoItem.completed
            repository.save(todoItem)
        }
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun createTodoItem(title: String) {
        repository.save(TodoItem(title = title))
    }

    fun getTodoItems(filter: ListFilter): List<TodoItemDto> {
        return when (filter) {
            ALL -> convertToDto(repository.findAll())
            ACTIVE -> convertToDto(repository.findAllByCompleted(false))
            COMPLETED -> convertToDto(repository.findAllByCompleted(true))
        }
    }

    private fun convertToDto(todoItems: List<TodoItem>): List<TodoItemDto> {
        return todoItems.map { TodoItemDto(it.id, it.title, it.completed) }
    }
}
package ch.teko.todospring.controller.rest

import ch.teko.todospring.controller.ui.dto.ListFilter
import ch.teko.todospring.controller.ui.dto.ListFilter.*
import ch.teko.todospring.controller.ui.dto.TodoItemDto
import ch.teko.todospring.controller.ui.dto.TodoItemFormData
import ch.teko.todospring.service.TodoItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class TodoItemRestController {

    @Autowired
    lateinit var service: TodoItemService

    @GetMapping
    fun index(): List<TodoItemDto> {
        return service.getTodoItems(ALL)
    }

    @GetMapping("/active")
    fun indexActive(): List<TodoItemDto> {
        return service.getTodoItems(ALL)
    }

    @GetMapping("/completed")
    fun indexCompleted(): List<TodoItemDto> {
        return service.getTodoItems(ALL)
    }

    @DeleteMapping("/completed")
    fun deleteCompletedItems() {
        service.deleteAllCompletedItems()
    }

    @PutMapping("/toggle-all")
    fun toggleAll() {
        service.toggleAll()
    }

    @PostMapping
    fun addNewTodoItem(@Valid @RequestBody todoItemDto: TodoItemDto) {
        service.createTodoItem(todoItemDto.title, todoItemDto.amount)
    }

    @PutMapping("/{id}/toggle")
    fun toggleSelection(@PathVariable("id") id: Long) {
        service.toggleSelectedItem(id)
    }

    @DeleteMapping("/{id}")
    fun deleteTodoItem(@PathVariable("id") id: Long) {
        service.delete(id)
    }

}
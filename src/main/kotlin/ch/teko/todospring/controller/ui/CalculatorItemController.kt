package ch.teko.todospring.controller.ui

import ch.teko.todospring.controller.ui.dto.ListFilter
import ch.teko.todospring.controller.ui.dto.ListFilter.*
import ch.teko.todospring.controller.ui.dto.TodoItemFormData
import ch.teko.todospring.service.CalculatorItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Controller
@RequestMapping("/")
class CalculatorItemController {

    @Autowired
    lateinit var service: CalculatorItemService

    @GetMapping
    fun index(model: Model): String? {
        addAttributesForIndex(model, ALL)
        service.getNumberOfActiveItems()
        return "index"
    }

    @GetMapping("/active")
    fun indexActive(model: Model): String {
        addAttributesForIndex(model, ACTIVE)
        return "index"
    }

    @GetMapping("/completed")
    fun indexCompleted(model: Model): String {
        addAttributesForIndex(model, COMPLETED)
        return "index"
    }

    @DeleteMapping("/completed")
    fun deleteCompletedItems(): String {
        service.deleteAllCompletedItems()
        return "redirect:/"
    }

    @PutMapping("/toggle-all")
    fun toggleAll(): String {
        service.toggleAll()
        return "redirect:/"
    }

    @PostMapping
    fun addNewTodoItem(@Valid @ModelAttribute("item") formData: TodoItemFormData): String? {
        service.createTodoItem(formData.title, formData.amount)
        return "redirect:/"
    }

    @PutMapping("/{id}/toggle")
    fun toggleSelection(@PathVariable("id") id: Long): String {
        service.toggleSelectedItem(id)
        return "redirect:/"
    }

    @DeleteMapping("/{id}")
    fun deleteTodoItem(@PathVariable("id") id: Long): String {
        service.delete(id)
        return "redirect:/"
    }

    private fun addAttributesForIndex(model: Model, listFilter: ListFilter) {
        model.addAttribute("item", TodoItemFormData())
        model.addAttribute("todos", service.getAusgabeItem())
        model.addAttribute("todos", service.getEingabeItem())
        model.addAttribute("totalNumberOfItems", service.count())
        model.addAttribute("filter", listFilter)
        model.addAttribute("todos", service.getAusgabeItem(listFilter))
        model.addAttribute("numberOfActiveItems", service.getNumberOfActiveItems())
        model.addAttribute("numberOfCompletedItems", service.getNumberOfCompletedItems())
    }

}
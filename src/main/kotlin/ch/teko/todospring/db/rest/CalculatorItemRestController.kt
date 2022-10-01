package ch.teko.todospring.db.rest

import ch.teko.todospring.controller.ui.dto.ListFilter.*
import ch.teko.todospring.controller.ui.dto.BetragItemDto
import ch.teko.todospring.service.CalculatorItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CalculatorItemRestController {

    @Autowired
    lateinit var service: CalculatorItemService

    @GetMapping
    fun index(): List<BetragItemDto> {
        return service.getAusgabeItem(ALL)
    }

    @GetMapping("/active")
    fun indexActive(): List<BetragItemDto> {
        return service.getAusgabeItem(ALL)
    }

    @GetMapping("/completed")
    fun indexCompleted(): List<BetragItemDto> {
        return service.getAusgabeItem(ALL)
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
    fun addNewBetragItem(@Valid @RequestBody betragItemDto: BetragItemDto) {
        service.createBetragItem(betragItemDto.title, betragItemDto.amount)
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
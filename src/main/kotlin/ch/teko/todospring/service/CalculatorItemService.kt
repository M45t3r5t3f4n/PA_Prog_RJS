package ch.teko.todospring.service

import java.time.Month
import ch.teko.todospring.controller.ui.dto.BetragItemDto
import ch.teko.todospring.controller.ui.dto.ListFilter
import ch.teko.todospring.controller.ui.dto.ListFilter.*
import ch.teko.todospring.db.CalculatorItem
import ch.teko.todospring.db.repository.CalculatorItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CalculatorItemService {

    @Autowired
    lateinit var repository: CalculatorItemRepository

    fun getNumberOfCompletedItems(): Int {
        return repository.countAllByCompleted(true)
    }
    fun getAll(): List<BetragItemDto> {
        return repository.findAll().map {
            BetragItemDto(it.id, it.title, it.amount, it.completed /*it.month*/)
        }
    }

    fun getAusgabeItem(): List<BetragItemDto> {
        return repository.findAll().map {
            BetragItemDto(it.id, it.title, it.amount, it.completed /*it.month*/)
        }
    }
    fun getEingabeItem(): List<BetragItemDto> {
        return repository.findAll().map {
            BetragItemDto(it.id, it.title, it.amount, it.completed, /*it.month*/)
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

    fun createBetragItem(title: String, amount : Double, /*month: Month*/) {
        repository.save(CalculatorItem(title = title, amount = amount, /*month = month*/))

    }


    fun getAusgabeItem(filter: ListFilter): List<BetragItemDto> {
        return when (filter) {
            ALL -> convertToDto(repository.findAll())
            ACTIVE -> convertToDto(repository.findAllByCompleted(false))
            COMPLETED -> convertToDto(repository.findAllByCompleted(true))
        }
    }

    private fun convertToDto(calculatorItems: List<CalculatorItem>): List<BetragItemDto> {
        return calculatorItems.map { BetragItemDto(it.id, it.title, it.amount, it.completed, /*it.month*/) }
    }
}
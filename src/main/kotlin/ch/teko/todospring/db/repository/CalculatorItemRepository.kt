package ch.teko.todospring.db.repository

import ch.teko.todospring.db.CalculatorItem
import org.springframework.data.jpa.repository.JpaRepository


interface CalculatorItemRepository : JpaRepository<CalculatorItem, Long> {
    fun countAllByCompleted(completed: Boolean): Int
    fun findAllByCompleted(completed: Boolean): List<CalculatorItem>
}
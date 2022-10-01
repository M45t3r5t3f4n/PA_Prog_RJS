package ch.teko.todospring.db

import java.time.Month
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank


@Entity
class CalculatorItem(
    @Id
    @GeneratedValue
    val id: Long? = null,
    @NotBlank
    val amount: Double,
    val title: String = "",
    var completed: Boolean = false,
   // val month: Month
)
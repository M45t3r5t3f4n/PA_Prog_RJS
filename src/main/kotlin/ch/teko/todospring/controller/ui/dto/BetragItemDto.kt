package ch.teko.todospring.controller.ui.dto

import java.time.Month
import javax.print.attribute.standard.DateTimeAtCreation

class BetragItemDto(
    val id: Long?,
    val title: String,
    val amount: Double,
    val completed: Boolean,
    //val month: DateTimeAtCreation,
)
package ch.teko.todospring.controller.ui.dto

import java.time.Month

class BetragItemDto(
    val id: Long?,
    val title: String,
    val amount: Double,
    val completed: Boolean,
)
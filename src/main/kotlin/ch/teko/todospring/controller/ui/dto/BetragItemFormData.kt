package ch.teko.todospring.controller.ui.dto

import javax.validation.constraints.NotBlank


class BetragItemFormData {

    @NotBlank
    var title: String = ""
    var amount: Double = 0.00
}
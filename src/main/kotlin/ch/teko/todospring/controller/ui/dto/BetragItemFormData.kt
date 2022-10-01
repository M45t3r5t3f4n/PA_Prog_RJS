package ch.teko.todospring.controller.ui.dto


import javax.print.attribute.standard.DateTimeAtCreation
import javax.validation.constraints.NotBlank


class BetragItemFormData {

    @NotBlank
    var title: String = ""
    var amount: Double = 0.00
    //val month: DateTimeAtCreation = TODO()

}
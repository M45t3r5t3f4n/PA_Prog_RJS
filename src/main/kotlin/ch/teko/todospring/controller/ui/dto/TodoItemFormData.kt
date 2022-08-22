package ch.teko.todospring.controller.ui.dto

import javax.validation.constraints.NotBlank


class TodoItemFormData {
    var title: @NotBlank String = ""
}
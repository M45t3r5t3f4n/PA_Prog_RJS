package ch.teko.todospring.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class CalculatorItemServiceTest {

    @Autowired
    lateinit var calculatorItemService: CalculatorItemService

    @Test
    fun `delete betrag item`() {
        // Arrange
        // Act
        calculatorItemService.delete(1)

        // Assert
        assertThat(calculatorItemService.getAll().size).isEqualTo()


}
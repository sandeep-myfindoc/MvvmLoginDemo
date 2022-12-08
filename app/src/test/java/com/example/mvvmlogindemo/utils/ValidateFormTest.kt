package com.example.mvvmlogindemo.utils

import org.junit.Assert.*

import org.junit.Test

class ValidateFormTest {

    @Test
    fun validatePassword() {
        val result = ValidateForm.validatePassword("  ")
        assertEquals(Pair(false,"Required Field"),result)
    }
}
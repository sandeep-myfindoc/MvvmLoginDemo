package com.example.mvvmlogindemo.utils

import org.junit.After
import org.junit.Test
import org.junit.Assert
import org.junit.Before

internal class HelperTest {
    @Before
    fun setUp(){
        // To run logic before the execution of test case
    }
    @Test
    fun isValidEmail() {
        // Arrange
        //Act
        val result = Helper.isValidEmail("sandeep")
        //Assert
        Assert.assertEquals(true,result)
    }

    @Test
    fun hideKeyboard() {
    }

    @After
    fun tearDown(){
        // To run logic after the execution of test case
    }
}

/*
*   Instead of writing multiple functions to test different input values
*   We can create a parameterized class which take i/p and expected o/p
*/
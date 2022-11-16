package com.example.mvvmlogindemo.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(value = Parameterized::class)
class HelperTestParameterized(val input: String,val expectedValue: Boolean) {
    @Test
    fun isValidEmail(){
        val result = Helper.isValidEmail(input)
        Assert.assertEquals(expectedValue,result)
    }

    companion object{
        @JvmStatic
        @Parameters
        fun data(): List<Array<Any>>
        {
            return listOf(
                arrayOf("",false),
                arrayOf("Sandeep",false),
                arrayOf("Sandeep",false),
                arrayOf("Sandeep@",false),
                arrayOf("Sandeep@gmail",false),
                arrayOf("Sandeep@gmail.",true),
                arrayOf("Sandeep@gmail.com",true))
        }
    }
}
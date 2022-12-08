package com.example.mvvmlogindemo.utils

object ValidateForm {
    fun validatePassword(input: String): Pair<Boolean,String>{
        return if(input.isBlank()){
            Pair(false,"Required Field")
        }else if(input.length<6){
            Pair(false,"Password length should not be less than six chars.")
        }else if(input.length>15){
            Pair(false,"Password length should not be greater than fifteen chars.")
        }else{
            Pair(true,"Valid")
        }
    }
}
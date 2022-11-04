package com.example.mvvmlogindemo.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmlogindemo.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

open class BaseActivity: AppCompatActivity() {
    protected var snackBar: Snackbar? = null
    protected fun startActivity(currentActivity: Context?, cls: Class<*>?) {
        val intent = Intent(currentActivity, cls)
        startActivity(intent)
    }
    protected fun displayMessage(id:Int,msg:String) {
        try{
            snackBar = Snackbar.make(findViewById(R.id.rootLayout), msg, Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            snackBar?.duration = BaseTransientBottomBar.LENGTH_LONG
            snackBar?.show()
        }catch(ex:Exception){
            ex.printStackTrace()
        }
    }
}
package com.example.mvvmlogindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogindemo.R
import com.example.mvvmlogindemo.databinding.ActivityLoginBinding
import com.example.mvvmlogindemo.network.ApiService
import com.example.mvvmlogindemo.network.RetrofitHelper
import com.example.mvvmlogindemo.receiver.ConnectivityReceiver
import com.example.mvvmlogindemo.repo.UserRepositry
import com.example.mvvmlogindemo.viewModel.LoginViewModel
import com.example.mvvmlogindemo.viewModelFactory.LoginViewModelFactory
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(),ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var snackBar: Snackbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val apiService = RetrofitHelper.getClient().create(ApiService :: class.java)
        val userRepositry = UserRepositry(apiService)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(userRepositry)).get(LoginViewModel:: class.java)
        viewModel.getUser().observe(this, Observer{
            Log.e("Login User: ", it.name+"     "+it.password)
        })
        binding.ref = viewModel
    }

    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this
    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(findViewById(R.id.rootLayout), "You are offline", Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.show()
        } else {
            snackBar?.dismiss()
        }
    }
}
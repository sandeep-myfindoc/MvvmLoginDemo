package com.example.mvvmlogindemo.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogindemo.R
import com.example.mvvmlogindemo.databinding.ActivityLoginBinding
import com.example.mvvmlogindemo.network.ApiService
import com.example.mvvmlogindemo.network.NetworkResult
import com.example.mvvmlogindemo.network.RetrofitHelper
import com.example.mvvmlogindemo.receiver.ConnectivityReceiver
import com.example.mvvmlogindemo.repo.UserRepositry
import com.example.mvvmlogindemo.utils.Helper
import com.example.mvvmlogindemo.viewModel.LoginViewModel
import com.example.mvvmlogindemo.viewModelFactory.LoginViewModelFactory
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class LoginActivity : BaseActivity(),ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val apiService = RetrofitHelper.getClient().create(ApiService :: class.java)
        val userRepositry = UserRepositry(apiService)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(userRepositry)).get(LoginViewModel:: class.java)
        binding.ref = viewModel
        binding.btnSignUp.setOnClickListener {
            startActivity(this,SignupActivity::class.java)
        }
        viewModel.errMessage.observe(this, Observer{
            if(it.toString().isNotEmpty()){
                if(it.toString().equals("Success"))
                    startActivity(this,HomeActivity::class.java)
                else
                    displayMessage(R.id.rootLayout,it.toString())
            }

        })
        viewModel.loginResponse.observe(this, Observer {
            displayMessage(R.id.rootLayout,it.toString())
            when(it){
                is NetworkResult.Loading->{binding.progressBar.visibility = View.VISIBLE}
                is NetworkResult.Sucess->{binding.progressBar.visibility = View.GONE }
                is NetworkResult.Error->{binding.progressBar.visibility = View.GONE}
            }
        })
        Helper.isValidEmail("Sandeep")
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        ConnectivityReceiver.connectivityReceiverListener = null
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
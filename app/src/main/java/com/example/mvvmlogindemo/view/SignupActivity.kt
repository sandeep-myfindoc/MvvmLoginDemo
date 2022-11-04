package com.example.mvvmlogindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogindemo.R
import com.example.mvvmlogindemo.databinding.ActivitySignupBinding
import com.example.mvvmlogindemo.network.ApiService
import com.example.mvvmlogindemo.network.RetrofitHelper
import com.example.mvvmlogindemo.receiver.ConnectivityReceiver
import com.example.mvvmlogindemo.repo.UserRepositry
import com.example.mvvmlogindemo.viewModel.SignupViewModel
import com.example.mvvmlogindemo.viewModelFactory.SignUpViewModelFactory
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class SignupActivity : BaseActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val apiService = RetrofitHelper.getClient().create(ApiService :: class.java)
        val userRepositry = UserRepositry(apiService)
        viewModel = ViewModelProvider(this,SignUpViewModelFactory(userRepositry)).get(SignupViewModel::class.java)
        binding.ref = viewModel
        binding.btnLogin.setOnClickListener {
            finish()
        }
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
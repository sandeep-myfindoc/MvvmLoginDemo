package com.example.mvvmlogindemo.network
import com.example.mvvmlogindemo.modal.loginResponse.LoginResponse
import com.example.mvvmlogindemo.modal.quoteResponse.QuoteResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// define the service here that you want to call
interface ApiService {
    @GET("EndPoint")
    suspend fun loginUser():Response<LoginResponse>

    @POST("EndPoint")
    suspend fun registerUser():Response<JSONObject>

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page: Int):Response<QuoteResponse>
}
/*
* There are three way to pass Header
* 1) Using Header annotaion along @GET or @POSt Annotation. we use it to pass static values in header
* 2) Pass Header inside the function call
* 3) to pass dynamic header we use intercept
* */
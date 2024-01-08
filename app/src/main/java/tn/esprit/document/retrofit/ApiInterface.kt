package tn.esprit.document.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/user/login")
    fun loginUser(@Body userData: UserLogin): Call<UserResponse>

    data class UserLogin(val email: String, val password: String)

    data class UserResponse(val email: String, val password: String)

}
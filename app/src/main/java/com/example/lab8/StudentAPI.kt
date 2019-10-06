package com.example.lab8

import android.telecom.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentAPI {
    @GET ("allemp")
    fun retrieveStudent(): retrofit2.Call<List<Employee>>

    @FormUrlEncoded
    @POST("emp")
    fun insertStd(
        @Field("emp_name") emp_name: String,
        @Field("emp_email") emp_email: String,
        @Field("emp_gen") emp_gen:String,
        @Field("emp_salary") emp_salary: Int): retrofit2.Call<Employee>
}
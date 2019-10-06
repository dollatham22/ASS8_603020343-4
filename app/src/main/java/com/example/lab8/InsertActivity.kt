package com.example.lab8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
    }

    fun addStudent(v : View){

        val radioGroup: RadioGroup = findViewById(R.id.radio_gd)
        val selectedId: Int = radioGroup.checkedRadioButtonId;
        val gender: RadioButton = findViewById(selectedId);

        val api : StudentAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentAPI ::class.java)

        api.insertStd(
            edt_name.text.toString(),
            gender.text.toString(),
            edt_email.text.toString(),
            edt_salary.text.toString().toInt()).enqueue(object : Callback<Employee> {
                override fun onResponse(call: Call<Employee>, response: retrofit2.Response<Employee>) {
                    if (response.isSuccessful()){
                        Toast.makeText(applicationContext, "Success Insert", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Employee>, t: Throwable) {
                    Toast.makeText(applicationContext,"Error onFailure" + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
        fun resetStudent(v: View){
            edt_name.getText().clear()
            radio_gd.clearCheck()
            edt_email.getText().clear()
            edt_salary.getText().clear()
        }
    }


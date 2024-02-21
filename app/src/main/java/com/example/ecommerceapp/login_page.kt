package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class login_page : AppCompatActivity() {
    private lateinit var email :EditText
    private lateinit var password :EditText
    private lateinit var loginBtn :Button
    private lateinit var signUpBtn :Button
    private lateinit var mAuth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        //Instentiating
        loginBtn = findViewById(R.id.login_btn)
        signUpBtn = findViewById(R.id.sign_up_btn)
        mAuth = Firebase.auth
        email = findViewById(R.id.email_id)
        password = findViewById(R.id.password)

        loginBtn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            login(email,password)
        }

        signUpBtn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            signUp(email,password)
        }


    }

    private fun signUp(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "account created", Toast.LENGTH_SHORT).show()
                } else {

                }
            }
    }

    private fun login(email: String, password: String) {

            mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent  = Intent(this,add_product::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "error occured", Toast.LENGTH_SHORT).show()
                    }
                }

    }

}
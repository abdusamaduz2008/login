package com.example.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.welcome.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.button.setOnClickListener {
            val email = binding.editTextText.text.toString()
            val pass = binding.editTextText2.text.toString()
            if (email.isNotEmpty()&&pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        val i = Intent(this,MainActivity3::class.java)
                        startActivity(i)
                        finish()
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"empty email or password",Toast.LENGTH_SHORT).show()
            }
        }
        binding.signup.setOnClickListener {
            val j = Intent(this,MainActivity4::class.java)
            startActivity(j)
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
        val data = firebaseAuth.currentUser
        if (data != null){
            startActivity(Intent(this,MainActivity3::class.java))
        }
    }
}
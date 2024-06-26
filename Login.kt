package com.example.wapda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_section_head_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views by ID
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginbtn)

        // Set onApplyWindowInsetsListener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set onClickListener for login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    if(isSectionHead()) {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        // Navigate to the next activity
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else if(isMeterReader())
                    {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        // Navigate to the next activity
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun isSectionHead(): Boolean {
        val db = FirebaseDatabase.getInstance().getReference()
        var value1=""
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                value1= snapshot.child("SectionHead").getValue().toString()
            }

            override fun onCancelled(error: DatabaseError) {
                //Toast.makeText(this@Login, "You are not a section head", Toast.LENGTH_SHORT).show()
            }


        })
        if(value1!!.equals(auth.currentUser!!.uid.toString()))
        {
            return true
        }
        else
        {
            return false
        }
    }
    private fun isMeterReader(): Boolean {
        val db = FirebaseDatabase.getInstance().getReference()
        var value1=""
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                value1= snapshot.child("MeterReader").getValue().toString()
            }

            override fun onCancelled(error: DatabaseError) {
                //Toast.makeText(this@Login, "You are not a section head", Toast.LENGTH_SHORT).show()
            }


        })
        if(value1!!.equals(auth.currentUser!!.uid.toString()))
        {
            return true
        }
        else
        {
            return false
        }
    }
}
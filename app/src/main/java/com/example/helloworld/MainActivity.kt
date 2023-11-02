package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth


/*class MainActivity : ComponentActivity() {
    private lateinit var binding: LayoutBinding
    val db = Firebase.firestore
    lateinit var username : EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener(View.OnClickListener {
            if (binding.username.text.toString() == "user" && binding.password.text.toString() == "1234"){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}*/
class MainActivity : ComponentActivity() {
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private lateinit var loginButton: Button

    //private ActivityMainBinding binding;
    private var firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        // binding = ActivityMainBinding.inflate(getLayoutInflater);
        // setContentView(binding.getRoot);
        editTextEmail = findViewById(R.id.username)
        editTextPassword = findViewById(R.id.password)
        loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener(View.OnClickListener {
            val email: String = java.lang.String.valueOf(editTextEmail.toString())
            val password: String = java.lang.String.valueOf(editTextPassword.toString())
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@MainActivity, "Enter email", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this@MainActivity, "Enter password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            // Call the loginUser method
            loginUser(email, password)
        })

    }

    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT).show()
                val homeIntent = Intent(this@MainActivity, HomepageActivity::class.java)
                startActivity(homeIntent)
                finish()
            } else {
                Toast.makeText(this@MainActivity, "Authentication failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}


package tn.esprit.document

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.document.databinding.ActivityMainBinding
import tn.esprit.document.retrofit.ApiInterface
import tn.esprit.document.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // CheckBox
        val rememberMeCheckBox = binding.loginCheckRemember
        rememberMeCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val email = binding.emailTextInputLayout.editText?.text.toString()
                val password = binding.passwordTextInputLayout.editText?.text.toString()
                saveCredentials(email, password)
            } else {
                clearCredentials()
            }
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailTextInputLayout.editText?.text.toString()
            val password = binding.passwordTextInputLayout.editText?.text.toString()

            if (!isValidEmail(email)) {
                binding.emailTextInputLayout.error = "Invalid email"
                return@setOnClickListener
            } else {
                binding.emailTextInputLayout.error = null
            }

            if (!isValidPassword(password)) {
                binding.passwordTextInputLayout.error = "Password must be at least 6 characters"
                return@setOnClickListener
            } else {
                binding.passwordTextInputLayout.error = null
            }

            if (isValidEmail(email) && isValidPassword(password)) {
                navigateToHomeActivity()
               // performLogin(email, password)
            }
        }  // fin btn login

    }

    private fun navigateToHomeActivity(){
       // val intent = Intent(this@MainActivity, HomeActivity2::class.java)
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    // functions of shared references
    private fun saveCredentials(email: String, password: String) {
        val sharedPref = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("email", email)
            putString("password", password)
            apply()
            Log.d("anas", "Email: $email, Password: $password")
        }
    }

    private fun clearCredentials() {
        val sharedPref = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove("email")
            remove("password")
            apply()
        }
    }


    // user Retrofit
    private fun performLogin (email: String, password: String){
        val call = RetrofitClient.instance.loginUser(ApiInterface.UserLogin(email, password))

        call.enqueue(object : Callback<ApiInterface.UserResponse> {
            override fun onResponse(call: Call<ApiInterface.UserResponse>, response: Response<ApiInterface.UserResponse>) {
                if (response.isSuccessful && response.body() != null) {
                   // saveLoginInfo(email) // Save the login info on successful login
                    navigateToHomeActivity()
                } else {
                    // The server did not find the user, show a login error
                    Log.d("404", "Not Found")
                }
            }

            override fun onFailure(call: Call<ApiInterface.UserResponse>, t: Throwable) {
                // Handle network error
                Log.d("500", "Internel server")
            }
        })
    }


}

package tn.esprit.document.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import tn.esprit.document.R

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }



    // Function to get the email from shared preference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userEmailTextView = view.findViewById<TextView>(R.id.txtEmail)
        val email = loadEmail()
        userEmailTextView.text = email ?: "No email saved"
    }


    private fun loadEmail(): String? {
        val sharedPref = activity?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        return sharedPref?.getString("email", null)
    }

}
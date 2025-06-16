package com.valsgroup.fleetx.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.valsgroup.fleetx.AuthActivity
import com.valsgroup.fleetx.R
import com.valsgroup.fleetx.navigation.NavigationManager

class RegisterFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var backButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupClickListeners()
    }

    private fun initViews(view: View) {
        nameEditText = view.findViewById(R.id.nameEditText)
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        registerButton = view.findViewById(R.id.registerButton)
        backButton = view.findViewById(R.id.backButton)
    }

    private fun setupClickListeners() {
        registerButton.setOnClickListener {
            handleRegister()
        }

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun handleRegister() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (name.isEmpty()) {
            NavigationManager.showToast(requireContext(), "Please enter your name")
            return
        }

        if (email.isEmpty()) {
            NavigationManager.showToast(requireContext(), "Please enter email")
            return
        }

        if (password.isEmpty()) {
            NavigationManager.showToast(requireContext(), "Please enter password")
            return
        }

        if (password.length < 6) {
            NavigationManager.showToast(requireContext(), "Password must be at least 6 characters")
            return
        }

        // Save user credentials
        NavigationManager.saveUserCredentials(requireContext(), name, email, password)
        
        // Navigate to OTP verification
        val otpFragment = OtpVerificationFragment.newInstance(email)
        (activity as AuthActivity).addFragment(otpFragment)
    }
} 
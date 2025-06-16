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

class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var rememberMeCheckBox: CheckBox
    private lateinit var loginButton: Button
    private lateinit var registerTextView: TextView
    private lateinit var forgotPasswordTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupClickListeners()
    }

    private fun initViews(view: View) {
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        rememberMeCheckBox = view.findViewById(R.id.rememberMeCheckBox)
        loginButton = view.findViewById(R.id.loginButton)
        registerTextView = view.findViewById(R.id.registerTextView)
        forgotPasswordTextView = view.findViewById(R.id.forgotPasswordTextView)
    }

    private fun setupClickListeners() {
        loginButton.setOnClickListener {
            handleLogin()
        }

        registerTextView.setOnClickListener {
            (activity as AuthActivity).addFragment(RegisterFragment())
        }

        forgotPasswordTextView.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isEmpty()) {
                NavigationManager.showToast(requireContext(), "Please enter your email first")
                return@setOnClickListener
            }
            
            // Navigate to OTP verification for password reset
            val otpFragment = OtpVerificationFragment.newInstanceForPasswordReset(email)
            (activity as AuthActivity).addFragment(otpFragment)
        }
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            NavigationManager.showToast(requireContext(), "Please enter email")
            return
        }

        if (password.isEmpty()) {
            NavigationManager.showToast(requireContext(), "Please enter password")
            return
        }

        if (NavigationManager.verifyCredentials(requireContext(), email, password)) {
            NavigationManager.setLoggedIn(requireContext(), true)
            NavigationManager.showToast(requireContext(), "Login successful")
            NavigationManager.navigateToMainScreen(requireContext())
        } else {
            NavigationManager.showToast(requireContext(), "Invalid email or password")
        }
    }
} 
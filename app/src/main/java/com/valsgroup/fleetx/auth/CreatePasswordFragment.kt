package com.valsgroup.fleetx.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.valsgroup.fleetx.R
import com.valsgroup.fleetx.navigation.NavigationManager

class CreatePasswordFragment : Fragment() {

    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var continueButton: Button
    private lateinit var backButton: ImageButton
    
    private var isPasswordReset: Boolean = false
    private var userEmail: String? = null

    companion object {
        private const val ARG_IS_PASSWORD_RESET = "is_password_reset"
        private const val ARG_EMAIL = "email"

        // For new account creation (called from OTP verification)
        fun newInstance(): CreatePasswordFragment {
            return CreatePasswordFragment()
        }

        // For password reset (called from forgot password)
        fun newInstanceForPasswordReset(email: String): CreatePasswordFragment {
            val fragment = CreatePasswordFragment()
            val args = Bundle()
            args.putBoolean(ARG_IS_PASSWORD_RESET, true)
            args.putString(ARG_EMAIL, email)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isPasswordReset = it.getBoolean(ARG_IS_PASSWORD_RESET, false)
            userEmail = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupClickListeners()
    }

    private fun initViews(view: View) {
        newPasswordEditText = view.findViewById(R.id.newPasswordEditText)
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText)
        continueButton = view.findViewById(R.id.continueButton)
        backButton = view.findViewById(R.id.backButton)
    }

    private fun setupClickListeners() {
        continueButton.setOnClickListener {
            handlePasswordCreation()
        }

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun handlePasswordCreation() {
        val newPassword = newPasswordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        if (newPassword.isEmpty()) {
            NavigationManager.showToast(requireContext(), "Please enter new password")
            return
        }

        if (confirmPassword.isEmpty()) {
            NavigationManager.showToast(requireContext(), "Please confirm your password")
            return
        }

        if (newPassword.length < 6) {
            NavigationManager.showToast(requireContext(), "Password must be at least 6 characters")
            return
        }

        if (newPassword != confirmPassword) {
            NavigationManager.showToast(requireContext(), "Passwords do not match")
            return
        }

        if (isPasswordReset) {
            // Handle password reset flow
            handlePasswordReset(newPassword)
        } else {
            // Handle new account creation flow
            handleNewAccountPasswordCreation(newPassword)
        }
    }

    private fun handlePasswordReset(newPassword: String) {
        val email = userEmail
        if (email != null) {
            // For password reset, we create a dummy name or get it from existing data
            val userName = NavigationManager.getUserName(requireContext()) ?: "User"
            NavigationManager.saveUserCredentials(requireContext(), userName, email, newPassword)
            NavigationManager.showToast(requireContext(), "Password reset successfully")
            
            // Navigate back to login screen
            requireActivity().supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } else {
            NavigationManager.showToast(requireContext(), "Error resetting password")
        }
    }

    private fun handleNewAccountPasswordCreation(newPassword: String) {
        // Update user password for new account creation
        val userEmail = NavigationManager.getUserEmail(requireContext())
        val userName = NavigationManager.getUserName(requireContext())
        
        if (userEmail != null && userName != null) {
            NavigationManager.saveUserCredentials(requireContext(), userName, userEmail, newPassword)
            NavigationManager.setLoggedIn(requireContext(), true)
            NavigationManager.showToast(requireContext(), "Account created successfully")
            NavigationManager.navigateToMainScreen(requireContext())
        } else {
            NavigationManager.showToast(requireContext(), "Error creating account")
        }
    }
} 
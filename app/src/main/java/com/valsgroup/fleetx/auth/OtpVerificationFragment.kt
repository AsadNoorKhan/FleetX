package com.valsgroup.fleetx.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.valsgroup.fleetx.AuthActivity
import com.valsgroup.fleetx.R
import com.valsgroup.fleetx.navigation.NavigationManager

class OtpVerificationFragment : Fragment() {

    private lateinit var emailTextView: TextView
    private lateinit var otp1EditText: EditText
    private lateinit var otp2EditText: EditText
    private lateinit var otp3EditText: EditText
    private lateinit var otp4EditText: EditText
    private lateinit var confirmButton: Button
    private lateinit var resendTextView: TextView
    private lateinit var backButton: ImageButton
    private lateinit var countdownTextView: TextView

    // Numeric keypad buttons
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnDelete: Button

    private var countDownTimer: CountDownTimer? = null
    private var userEmail: String? = null
    private var currentFocusIndex = 0
    private var isPasswordReset: Boolean = false

    companion object {
        private const val ARG_EMAIL = "email"
        private const val ARG_IS_PASSWORD_RESET = "is_password_reset"

        // For registration flow
        fun newInstance(email: String): OtpVerificationFragment {
            val fragment = OtpVerificationFragment()
            val args = Bundle()
            args.putString(ARG_EMAIL, email)
            args.putBoolean(ARG_IS_PASSWORD_RESET, false)
            fragment.arguments = args
            return fragment
        }

        // For forgot password flow
        fun newInstanceForPasswordReset(email: String): OtpVerificationFragment {
            val fragment = OtpVerificationFragment()
            val args = Bundle()
            args.putString(ARG_EMAIL, email)
            args.putBoolean(ARG_IS_PASSWORD_RESET, true)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userEmail = it.getString(ARG_EMAIL)
            isPasswordReset = it.getBoolean(ARG_IS_PASSWORD_RESET, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_otp_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupClickListeners()
        setupOtpInputs()
        setupNumericKeypad()
        startCountdown()
        updateOtpStyling()
        
        // Display the email
        emailTextView.text = getString(R.string.otp_subtitle).replace("Ex:coffee@gmail.com", userEmail ?: "")
    }

    private fun initViews(view: View) {
        emailTextView = view.findViewById(R.id.emailTextView)
        otp1EditText = view.findViewById(R.id.otp1EditText)
        otp2EditText = view.findViewById(R.id.otp2EditText)
        otp3EditText = view.findViewById(R.id.otp3EditText)
        otp4EditText = view.findViewById(R.id.otp4EditText)
        confirmButton = view.findViewById(R.id.confirmButton)
        resendTextView = view.findViewById(R.id.resendTextView)
        backButton = view.findViewById(R.id.backButton)
        countdownTextView = view.findViewById(R.id.countdownTextView)

        // Initialize keypad buttons
        btn0 = view.findViewById(R.id.btn0)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)
        btn5 = view.findViewById(R.id.btn5)
        btn6 = view.findViewById(R.id.btn6)
        btn7 = view.findViewById(R.id.btn7)
        btn8 = view.findViewById(R.id.btn8)
        btn9 = view.findViewById(R.id.btn9)
        btnDelete = view.findViewById(R.id.btnDelete)
    }

    private fun setupClickListeners() {
        confirmButton.setOnClickListener {
            handleOtpConfirmation()
        }

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        resendTextView.setOnClickListener {
            if (resendTextView.isEnabled) {
                resendOtp()
            }
        }
    }

    private fun setupNumericKeypad() {
        // Set up number button clicks
        btn0.setOnClickListener { insertNumber("0") }
        btn1.setOnClickListener { insertNumber("1") }
        btn2.setOnClickListener { insertNumber("2") }
        btn3.setOnClickListener { insertNumber("3") }
        btn4.setOnClickListener { insertNumber("4") }
        btn5.setOnClickListener { insertNumber("5") }
        btn6.setOnClickListener { insertNumber("6") }
        btn7.setOnClickListener { insertNumber("7") }
        btn8.setOnClickListener { insertNumber("8") }
        btn9.setOnClickListener { insertNumber("9") }
        
        // Set up delete button
        btnDelete.setOnClickListener { deleteNumber() }
    }

    private fun insertNumber(number: String) {
        val otpInputs = listOf(otp1EditText, otp2EditText, otp3EditText, otp4EditText)
        
        // Find the first empty field or current focus
        for (i in otpInputs.indices) {
            if (otpInputs[i].text.toString().isEmpty()) {
                otpInputs[i].setText(number)
                currentFocusIndex = if (i < otpInputs.size - 1) i + 1 else i
                updateOtpStyling()
                return
            }
        }
    }

    private fun deleteNumber() {
        val otpInputs = listOf(otp1EditText, otp2EditText, otp3EditText, otp4EditText)
        
        // Find the last filled field and clear it
        for (i in otpInputs.indices.reversed()) {
            if (otpInputs[i].text.toString().isNotEmpty()) {
                otpInputs[i].setText("")
                currentFocusIndex = i
                updateOtpStyling()
                return
            }
        }
    }

    private fun updateOtpStyling() {
        val otpInputs = listOf(otp1EditText, otp2EditText, otp3EditText, otp4EditText)
        
        otpInputs.forEachIndexed { index, editText ->
            if (editText.text.toString().isNotEmpty()) {
                // Has text - show orange border
                editText.background = ContextCompat.getDrawable(requireContext(), R.drawable.otp_input_selected)
            } else {
                // Empty - show gray border
                editText.background = ContextCompat.getDrawable(requireContext(), R.drawable.otp_input_background)
            }
        }
    }

    private fun setupOtpInputs() {
        val otpInputs = listOf(otp1EditText, otp2EditText, otp3EditText, otp4EditText)

        otpInputs.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateOtpStyling()
                    if (s?.length == 1 && index < otpInputs.size - 1) {
                        currentFocusIndex = index + 1
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                    if (editText.text.isEmpty() && index > 0) {
                        otpInputs[index - 1].setText("")
                        currentFocusIndex = index - 1
                        updateOtpStyling()
                    }
                }
                false
            }

            // Disable keyboard input to force use of custom keypad
            editText.showSoftInputOnFocus = false
        }
    }

    private fun startCountdown() {
        resendTextView.isEnabled = false
        countDownTimer = object : CountDownTimer(48000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                countdownTextView.text = getString(R.string.resend_code, seconds)
            }

            override fun onFinish() {
                resendTextView.isEnabled = true
                countdownTextView.text = getString(R.string.didnt_receive_email)
            }
        }.start()
    }

    private fun resendOtp() {
        NavigationManager.showToast(requireContext(), "OTP sent to your email")
        startCountdown()
    }

    private fun handleOtpConfirmation() {
        val otp = otp1EditText.text.toString() + 
                 otp2EditText.text.toString() + 
                 otp3EditText.text.toString() + 
                 otp4EditText.text.toString()

        if (otp.length != 4) {
            NavigationManager.showToast(requireContext(), "Please enter complete OTP")
            return
        }

        // For demo purposes, accept any 4-digit OTP
        // In real app, verify with server
        NavigationManager.showToast(requireContext(), "OTP verified successfully")
        
        if (isPasswordReset) {
            // Navigate to Create Password for password reset
            val createPasswordFragment = CreatePasswordFragment.newInstanceForPasswordReset(userEmail ?: "")
            (activity as AuthActivity).addFragment(createPasswordFragment)
        } else {
            // Registration complete - navigate back to login page
            NavigationManager.showToast(requireContext(), "Registration completed successfully. Please login to continue.")
            
            // Clear the back stack and return to login screen
            requireActivity().supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
} 
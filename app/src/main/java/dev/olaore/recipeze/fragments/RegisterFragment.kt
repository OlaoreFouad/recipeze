package dev.olaore.recipeze.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

import dev.olaore.recipeze.R
import dev.olaore.recipeze.databinding.FragmentRegisterBinding
import dev.olaore.recipeze.viewmodels.RegisterViewModel

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            usernameInput.setText(viewModel.getUser().username)
            pinInput.setText(viewModel.getUser().pin)
            usernameInput.addTextChangedListener(this@RegisterFragment)
            pinInput.addTextChangedListener(this@RegisterFragment)
            registerButton.setOnClickListener { onRegister() }
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        var username = binding.usernameInput.text.toString()
        var pin = binding.pinInput.text.toString()

        viewModel.createUser(username, pin)

        binding.registerButton.isEnabled = !username.isNullOrBlank() && pin.length == 4
    }

    fun onRegister() {
        var user = viewModel.getUser()
        Snackbar.make(binding.registerButton, "Username: ${user.username} PIN: ${user.pin}", Snackbar.LENGTH_LONG).show()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

}

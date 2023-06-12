package com.example.laboratorio11.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.laboratorio11.R
import com.example.laboratorio11.databinding.FragmentRegisterBinding
import com.example.laboratorio11.ui.login.LoginUiStatus
import com.example.laboratorio11.ui.register.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar


class RegisterFragment : Fragment() {
    val registerViewModel by activityViewModels<RegisterViewModel> {
        RegisterViewModel.Factory
    }
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        observeStatus()
    }

    fun setViewModel () {
        binding.viewmodel = registerViewModel
    }

    fun observeStatus() {
        registerViewModel.status.observe(viewLifecycleOwner){state ->
            handleUiStatus(state)
        }
    }
    fun handleUiStatus(state: RegisterUiStatus) {
        when(state) {
            is RegisterUiStatus.Error -> {
                Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT).show()
            }
            is RegisterUiStatus.ErrorWithMessage -> {
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
            is RegisterUiStatus.Success -> {
                registerViewModel.clearStatus()
                registerViewModel.clearData()
                findNavController().popBackStack()
            }

            else -> {}
        }
    }
}
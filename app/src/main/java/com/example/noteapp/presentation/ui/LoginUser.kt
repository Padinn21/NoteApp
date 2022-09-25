package com.example.noteapp.presentation.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentLoginUserBinding


class LoginUser : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private var _binding: FragmentLoginUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().applicationContext.getSharedPreferences("datauser", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            if (email == sharedPreferences.getString("email","") && password == sharedPreferences.getString("password","") ){
                it.findNavController().navigate(R.id.action_loginUser2_to_homeFragment)
            } else {
                Toast.makeText(activity, "Invalid Username Or Password", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginUser2_to_registerUser2)
        }
    }


}
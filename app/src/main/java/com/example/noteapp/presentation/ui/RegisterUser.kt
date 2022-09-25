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
import com.example.noteapp.databinding.FragmentRegisterUserBinding

class RegisterUser : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private var _binding: FragmentRegisterUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterUserBinding.inflate(inflater,container,false)
        val view =binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().applicationContext.getSharedPreferences("datauser",
            Context.MODE_PRIVATE)
        binding.btnDaftar.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val repeatpassword = binding.edtKonfirmasiPassword.text.toString()
            var addData = sharedPreferences.edit()

            if (password == repeatpassword) {
                addData.putString("username",username)
                addData.putString("email",email)
                addData.putString("password",password)
                addData.apply()
                it.findNavController().navigate(R.id.action_registerUser2_to_loginUser2)
                Toast.makeText(activity, "Register Sucessfully", Toast.LENGTH_SHORT).show()
            } else if (password != repeatpassword) {
                Toast.makeText(activity, "Unmatched Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
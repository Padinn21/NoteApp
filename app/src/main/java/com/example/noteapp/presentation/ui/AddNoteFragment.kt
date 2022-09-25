package com.example.noteapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.data.room.Note
import com.example.noteapp.data.room.NoteDatabase
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteFragment : Fragment() {
    lateinit var noteVm : NoteViewModel

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    var dbNote : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteVm = ViewModelProvider(this).get(NoteViewModel::class.java)

        dbNote = NoteDatabase.getInstance(requireActivity())

        binding.btnAdd.setOnClickListener {
            addNote()
        }

    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.edtJudul.text.toString()
            var content = binding.edtCatatan.text.toString()
            noteVm.insertNote(Note(0,title,content))
        }
        findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
    }

}
package com.example.noteapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.data.room.Note
import com.example.noteapp.data.room.NoteDatabase
import com.example.noteapp.databinding.FragmentEditBinding
import com.example.noteapp.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    var dbNote: NoteDatabase? = null
    lateinit var noteVm : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteVm = ViewModelProvider(this).get(NoteViewModel::class.java)

        dbNote = NoteDatabase.getInstance(requireActivity())
        var getDataNote = arguments?.getSerializable("dataedit") as Note

        binding.edtJudul.setText(getDataNote.title)
        binding.edtCatatan.setText(getDataNote.content)
        binding.id.setText(getDataNote.id.toString())
        binding.btnEdit.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        var id = binding.id.text.toString().toInt()
        var title = binding.edtJudul.text.toString()
        var content = binding.edtCatatan.text.toString()


        GlobalScope.async {
            noteVm.updateNote(Note(id,title,content))
            activity?.runOnUiThread {
                Toast.makeText(requireActivity(), "Edit Successfully", Toast.LENGTH_LONG).show()
            }
        }
        findNavController().navigate(R.id.action_editFragment_to_homeFragment)
    }
}
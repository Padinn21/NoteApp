package com.example.noteapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var content: String
): Serializable
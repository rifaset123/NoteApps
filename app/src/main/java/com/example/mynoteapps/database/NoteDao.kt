package com.example.mynoteapps.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    // CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignore jika ada data yang sama
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    // optional, menjalankan intruksi atau perintah untuk mengeksekusi sebuah aksi dengan anotasi @Query.
    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}
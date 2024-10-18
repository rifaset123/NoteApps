package com.example.mynoteapps.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapps.ui.insert.NoteAddUpdateViewModel
import com.example.mynoteapps.ui.main.MainViewModel

// menambahkan context ketika memanggil kelas ViewModel di dalam Activity

/*
* mengirim context ke dalam ViewModel yang nantinya digunakan
* untuk inisialisasi database di dalam NoteRepository.
*/

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    // mengirimkan parameter context dengan nama mApplication ke kelas MainViewModel
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java)) {
            return NoteAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
package com.example.librarymanagementapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementapplication.models.Book

class DashboardViewModel : ViewModel() {
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    init {
        _books.value = listOf(
            Book("Sách 01", true),
            Book("Sách 02", true)
        )
    }

    fun updateBookStatus(position: Any, isChecked: Any) {
        _books.value = _books.value?.mapIndexed { index, book ->
            if (index == position) book.copy(isChecked = isChecked) else book
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}
package com.example.librarymanagementapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementapplication.R
import com.example.librarymanagementapplication.models.Book

class BookAdapter(private val bookList: MutableList<Book>, param: (Any, Any) -> Unit) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookCheckBox: CheckBox = itemView.findViewById(R.id.bookCheckBox)
        val bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.bookTitle.text = book.name
        holder.bookCheckBox.isChecked = book.isChecked as Boolean

        holder.bookCheckBox.setOnCheckedChangeListener { _, isChecked ->
            book.isChecked = isChecked
        }
    }

    fun submitList(newList: List<Book>) {
        bookList.clear()
        bookList.addAll(newList)
        notifyDataSetChanged() // Thông báo dữ liệu thay đổi
    }

    override fun getItemCount() = bookList.size
}

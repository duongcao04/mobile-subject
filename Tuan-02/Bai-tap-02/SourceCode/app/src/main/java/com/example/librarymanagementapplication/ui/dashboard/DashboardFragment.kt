package com.example.librarymanagementapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.librarymanagementapplication.R
import com.example.librarymanagementapplication.databinding.FragmentDashboardBinding
import com.example.librarymanagementapplication.adapters.BookAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardFragment : Fragment() {
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.bookRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Khởi tạo Adapter một lần
        bookAdapter = BookAdapter(mutableListOf()) { position, isChecked ->
            dashboardViewModel.updateBookStatus(position, isChecked)
        }
        recyclerView.adapter = bookAdapter

        // Quan sát danh sách từ ViewModel và cập nhật Adapter
        dashboardViewModel.books.observe(viewLifecycleOwner) { bookList ->
            bookAdapter.submitList(bookList) // Chỉ cập nhật danh sách, không tạo lại Adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
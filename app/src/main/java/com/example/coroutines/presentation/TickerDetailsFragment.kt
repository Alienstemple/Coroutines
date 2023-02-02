package com.example.coroutines.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coroutines.databinding.FragmentTickerDetailsBinding

class TickerDetailsFragment : Fragment() {
    private var _binding: FragmentTickerDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTickerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closeBtn.setOnClickListener {
            navigator().hideTickerDetails()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TickerDetailsFragment()
    }
}
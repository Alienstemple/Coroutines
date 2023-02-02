package com.example.coroutines.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coroutines.databinding.FragmentTickerDetailsBinding
import com.example.coroutines.presentation.vm.TickerDetailsViewModel
import com.example.coroutines.presentation.vm.TickerViewModelFactory

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

        val tickerInteractor = navigator().getTickerInteractor()
        val tickerDetailsViewModel: TickerDetailsViewModel =
            ViewModelProvider(this,
                TickerViewModelFactory(tickerInteractor))[TickerDetailsViewModel::class.java]

        setupObserver(tickerDetailsViewModel)

        binding.closeBtn.setOnClickListener {
            navigator().hideTickerDetails()
        }
    }

    private fun setupObserver(tickerDetailsViewModel: TickerDetailsViewModel) {
//        tickerDetailsViewModel.tickerList.observe(this) { tickersList ->
//            tickersList?.let {
//                // Обновляем Recycler View
//                tickerAdapter.setList(it)
//            }
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TickerDetailsFragment()
    }
}
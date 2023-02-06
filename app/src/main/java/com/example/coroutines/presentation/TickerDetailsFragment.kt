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

private const val ARG_PARAM_SYMBOL = "symbol"

class TickerDetailsFragment : Fragment() {

    private var symbol: String? = null

    private var _binding: FragmentTickerDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            symbol = it.getString(ARG_PARAM_SYMBOL)
        }
    }

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

        // TODO where to call view model?
        tickerDetailsViewModel.getTickerDetails(symbol)

        binding.closeBtn.setOnClickListener {
            navigator().hideTickerDetails()
        }
    }

    private fun setupObserver(tickerDetailsViewModel: TickerDetailsViewModel) {
        tickerDetailsViewModel.ticker.observe(viewLifecycleOwner) { tickersList ->
            tickersList?.let {
                // Обновим UI - передадим
                binding.nameTv.text = it.toString()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(symbol: String?) =
            TickerDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_SYMBOL, symbol)
                }
            }
    }
}
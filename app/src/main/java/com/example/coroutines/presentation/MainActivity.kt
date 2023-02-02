package com.example.coroutines.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.data.TickerRepositoryImpl
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.domain.TickerInteractorImpl
import com.example.coroutines.presentation.vm.TickerViewModelFactory
import com.example.coroutines.presentation.vm.TickersViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var tickerAdapter: TickersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val tickerRepository = TickerRepositoryImpl()  // TODO можем ли здесь создать экземпляры?
        val tickerInteractor = TickerInteractorImpl(tickerRepository)

        val tickersViewModel: TickersViewModel =
            ViewModelProvider(this,
                TickerViewModelFactory(tickerInteractor))[TickersViewModel::class.java]

        // Init adapter for recycler
        initAdapter()

        // Init observer
        setupObserver(tickersViewModel)

        mainBinding.getRetrofitBtn.setOnClickListener {
            tickersViewModel.getTickersAndQuotes(this.applicationContext)
        }
    }

    private fun setupObserver(tickersViewModel: TickersViewModel) {
        tickersViewModel.tickerList.observe(this, Observer { tickersList ->
            tickersList?.let {
                // Обновляем Recycler View
                tickerAdapter.setList(it)
            }
        })
    }

    private fun initAdapter() {
        tickerAdapter = TickersAdapter()
        mainBinding.tickersRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainBinding.tickersRecycler.adapter = tickerAdapter
    }

    companion object {
        const val TAG = "MainActLog"
    }
}
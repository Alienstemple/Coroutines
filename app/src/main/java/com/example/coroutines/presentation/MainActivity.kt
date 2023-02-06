package com.example.coroutines.presentation

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import com.example.coroutines.data.*
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.domain.TickerInteractorImpl
import com.example.coroutines.models.domain.TickerOutput
import com.example.coroutines.presentation.vm.TickerViewModelFactory
import com.example.coroutines.presentation.vm.TickersViewModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), Navigator, MyItemClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var tickerAdapter: TickersAdapter

    private val mapper = jacksonObjectMapper()
    private val tickerFileService = TickerFileService(mapper)
    private val tickerNetworkService = TickerNetworkService(RetrofitService.getInstance())

    private val tickerRepository = TickerNetworkRepositoryImpl(tickerNetworkService)
    private val tickerFileRepository = TickerFileRepositoryImpl(tickerFileService)
    private val tickerInteractor = TickerInteractorImpl(tickerRepository, tickerFileRepository)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val tickersViewModel: TickersViewModel =
            ViewModelProvider(this,
                TickerViewModelFactory(tickerInteractor))[TickersViewModel::class.java]

        // Init adapter for recycler
        initAdapter()

        // Init observer
        setupObserver(tickersViewModel)

        mainBinding.getRetrofitBtn.setOnClickListener {
//            showTickerDetails()
            tickersViewModel.getTickersAndQuotes(this.applicationContext)

            // 3 -- collect value of state flow
//            tickersViewModel.tickerFlow.collect()
            lifecycleScope.launch {
                tickersViewModel.tickerFlow.collectLatest {

                }

            }
        }
    }

    private fun setupObserver(tickersViewModel: TickersViewModel) {
        tickersViewModel.tickerList.observe(this) { tickersList ->
            tickersList?.let {
                // Обновляем Recycler View
                tickerAdapter.setList(it)
            }
        }
    }

    private fun initAdapter() {
        tickerAdapter = TickersAdapter(this)
        mainBinding.tickersRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainBinding.tickersRecycler.adapter = tickerAdapter
    }

    companion object {
        const val TAG = "MainActLog"
    }

    override fun showTickerDetails(tickerOutput: TickerOutput) {
        Log.d(TAG, "Item Clicked, in showTickerDetails, ticker = ${tickerOutput.name}")
        mainBinding.detailsFragContainer.visibility = View.VISIBLE
        launchFragment(TickerDetailsFragment.newInstance(tickerOutput.symbol))  // TODO pass tickerOutputId!
    }

    override fun hideTickerDetails() {
        mainBinding.detailsFragContainer.visibility = View.GONE
        removeFragment(TickerDetailsFragment())
    }

    override fun getTickerInteractor(): TickerInteractor = tickerInteractor

    private fun launchFragment(fragment: Fragment) {
        Log.d(TAG, "Transact with name ${fragment::class.java.simpleName}")
        supportFragmentManager.beginTransaction()
            .replace(R.id.details_frag_container, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    private fun removeFragment(fragment: Fragment) {
        Log.d(TAG, "Remove frag called")
        supportFragmentManager.popBackStack(fragment::class.java.simpleName,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onItemClicked(tickerOutput: TickerOutput) {
        showTickerDetails(tickerOutput)
    }
}
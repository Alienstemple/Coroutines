package com.example.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.data.Ticket
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.service.NetworkService
import com.example.coroutines.vm.TicketsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var ticketAdapter: TicketsAdapter

    private val ticketsViewModel: TicketsViewModel by viewModels()

    private var ticketList: List<Ticket> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.getBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                Log.d(TAG, "coro launched")
                val testData: String
                val res1: String
                val res2: String
                val call1 = async { NetworkService().getTickets() }
                val call2 = async { NetworkService().getQuotes() }

                res1 = call1.await()
                res2 = call2.await()
                testData = res1 + res2

                withContext(Dispatchers.Main) {
                    // Update view model
                    ticketsViewModel.testData.value = testData
                    Log.d(TAG, "LiveData updated")
                }

            }

            ticketsViewModel.testData.observe(this, Observer {
                mainBinding.textView.text = it
            })
        }
    }

    private fun updateTicketRecycler() {
        ticketAdapter = TicketsAdapter(ticketList)

        mainBinding.ticketsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainBinding.ticketsRecycler.adapter = ticketAdapter
    }

    companion object {
        const val TAG = "MainActLog"
    }
}
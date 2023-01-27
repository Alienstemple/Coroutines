package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.data.Ticket
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.service.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var ticketAdapter: TicketsAdapter

    private var ticketList: List<Ticket> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.getBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                Log.d(TAG, "coro launched")
                NetworkService().getTickets()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
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
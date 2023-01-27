package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.data.Ticket
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.service.NetworkService

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var ticketAdapter: TicketsAdapter

    private var ticketList: List<Ticket> = emptyList()

    private val backgroundHandler: Handler
    private val uiHandler =
        Handler(Looper.getMainLooper())  // Основной поток уже запущен. Возьмем от него looper

    private val getTicketsFromNetwork = Runnable {
        ticketList = NetworkService().getTickets()
        uiHandler.post(updateUI)
    }

    private val updateUI = Runnable {
        updateTicketRecycler()
    }

    init {
        val backgroundHandlerThread = HandlerThread("background")
        backgroundHandlerThread.start()    // Запустим фоновый поток
        backgroundHandler =
            Handler(backgroundHandlerThread.looper)   // В конструктор передадим looper запущенного фонового потока
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.getBtn.setOnClickListener {
            backgroundHandler.post(getTicketsFromNetwork)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundHandler.removeCallbacksAndMessages(null)
        uiHandler.removeCallbacksAndMessages(null)
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
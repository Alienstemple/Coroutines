package com.example.coroutines.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutines.data.Ticket

class TicketsViewModel: ViewModel() {
    val testData = MutableLiveData<String>()
}
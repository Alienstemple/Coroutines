package com.example.coroutines

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.data.Ticket
import com.example.coroutines.databinding.TicketItemBinding

class TicketsAdapter (private val ticketsList: List<Ticket>): RecyclerView.Adapter<TicketsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder() called with: parent = $parent, viewType = $viewType")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ticketsList[position])
    }

    override fun getItemCount() = ticketsList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val ticketItemBinding = TicketItemBinding.bind(view)

        fun bind(ticketItem: Ticket) = with (ticketItemBinding) {
            Log.d(TAG, "bind() called ${ticketItem.name}")
            ticketNameTv.text = ticketItem.name

//            Picasso.get().load(pictureItem.url)
//                .into(img1)
        }
    }

    companion object {
        const val TAG = "TickAdaptLog"
    }
}
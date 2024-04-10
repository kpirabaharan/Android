package com.example.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.databinding.ListItemBinding
import com.example.roomdemo.db.Subscriber

class MyRecyclerViewAdapter(
        private val subscribersList: List<Subscriber>,
        private val clickListener: (Subscriber) -> Unit,
) : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.bind(subscribersList[position], clickListener)
        }

        override fun getItemCount(): Int {
                return subscribersList.size
        }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
                binding.nameTextView.text = subscriber.name
                binding.emailTextView.text = subscriber.email
                binding.listItemLayout.setOnClickListener {
                        clickListener(subscriber)
                }
        }
}
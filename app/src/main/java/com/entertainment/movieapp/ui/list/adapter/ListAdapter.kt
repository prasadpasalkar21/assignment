package com.entertainment.movieapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ccpp.shared.entities.Row
import com.entertainment.movieapp.R
import com.entertainment.movieapp.databinding.RowLayoutListBinding

class ListAdapter(
    private val bookingListList: ArrayList<Row>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    fun addData(listItems: ArrayList<Row>) {
        val size = listItems.size
        bookingListList.addAll(listItems)
        val sizeNew = listItems.size
        notifyItemRangeChanged(size, sizeNew)
    }


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_layout_list,
                parent,
                false
            )
        )
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {

        holder.bindItems(bookingListList[position])


    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return bookingListList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(private val binding: RowLayoutListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(movieList: Row) {
            binding.model = movieList
            
        }
    }
}
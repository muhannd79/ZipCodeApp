package org.fooshtech.zipcodeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.fooshtech.zipcodeapp.databinding.ItemCardviewLayoutBinding
import org.fooshtech.zipcodeapp.model.ZipCodeItem

class ZipCodeAdapter(private var zipCodes: List<ZipCodeItem>) :
    RecyclerView.Adapter<ZipCodeAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = ItemCardviewLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(zipCodes[position])
    }


    override fun getItemCount(): Int {
        return zipCodes.size
    }

    fun setData(result: List<ZipCodeItem>) {

        zipCodes = result
        notifyDataSetChanged()

    }

    class MyViewHolder(private val binding: ItemCardviewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ZipCodeItem) {
            binding.zipCodeInfoCard.text = item.zipCode
            binding.cityInfoCardValue.text = item.city
            binding.stateInfoCardValue.text = item.state
            binding.distanceInfoCardValue.text = item.distance.toString()
        }

    }


}

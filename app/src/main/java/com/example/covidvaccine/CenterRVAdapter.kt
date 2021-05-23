package com.example.covidvaccine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CenterRVAdapter(private val centerList:List<CenterRvModal>):
    RecyclerView.Adapter<CenterRVAdapter.CenterRVViewHolder>()
{
    class CenterRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val centerNameTV: TextView = itemView.findViewById(R.id.CenterName)
        val centerAddressTV: TextView = itemView.findViewById(R.id.CenterAddress)
        val centerTimings: TextView = itemView.findViewById(R.id.CenterTimings)
        val vaccineNameTV: TextView = itemView.findViewById(R.id.VaccineName)
        val centerAgeLimitTV: TextView = itemView.findViewById(R.id.AgeLimit)
        val centerFeeTypeTV: TextView = itemView.findViewById(R.id.FeeType)
        val avalabilityTV: TextView = itemView.findViewById(R.id.Avaliablity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterRVViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.center_rv_item,
            parent, false
        )
        return CenterRVViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return centerList.size
    }
    override fun onBindViewHolder(holder: CenterRVViewHolder, position: Int) {
        val currentItem = centerList[position]
        holder.centerNameTV.text = currentItem.centerName
        holder.centerAddressTV.text = currentItem.centerAddress
        holder.centerTimings.text = ("From : " + currentItem.centerFromTime + " To : " + currentItem.centerToTime)
        holder.vaccineNameTV.text = currentItem.vaccineName
        holder.centerAgeLimitTV.text = "Age Limit : " + currentItem.ageLimit.toString()
        holder.centerFeeTypeTV.text = currentItem.fee_type
        holder.avalabilityTV.text = "Availability : " + currentItem.availableCapacity.toString()
    }
}
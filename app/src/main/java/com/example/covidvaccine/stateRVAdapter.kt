package com.example.covidvaccine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class stateRVAdapter(private val stateList: List<StateModel>) :
    RecyclerView.Adapter<stateRVAdapter.StateRVviewholder>() {
    class StateRVviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val stateName: TextView = itemview.findViewById(R.id.state)
        val cases: TextView = itemview.findViewById(R.id.StateCases)
        val death: TextView = itemview.findViewById(R.id.StateDeaths)
        val recovered: TextView = itemview.findViewById(R.id.StateRecovered)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateRVviewholder {
        val View: View =
            LayoutInflater.from(parent.context).inflate(R.layout.statervitem, parent, false)
        return StateRVviewholder(View)
    }


    override fun onBindViewHolder(holder: stateRVAdapter.StateRVviewholder, position: Int) {
        val stateData = stateList[position]
        holder.cases.text = stateData.cases.toString()
        holder.stateName.text = stateData.state
        holder.death.text = stateData.deaths.toString()
        holder.recovered.text = stateData.recovered.toString()

    }

    override fun getItemCount(): Int {
        return stateList.size
    }
}
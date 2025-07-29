package com.example.lastgym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseHistoryAdapter(
    private val historyData: List<Map<String, Any>>
) : RecyclerView.Adapter<ExerciseHistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateText: TextView = itemView.findViewById(R.id.history_date)
        val detailsText: TextView = itemView.findViewById(R.id.history_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = historyData[position]
        
        val date = historyItem["date"] as String
        val weight = historyItem["weight"] as Double
        val repetitions = historyItem["repetitions"] as Int
        val sets = historyItem["sets"] as Int
        
        holder.dateText.text = date
        holder.detailsText.text = "الوزن: $weight كجم، التكرارات: $repetitions، الجلسات: $sets"
    }

    override fun getItemCount(): Int = historyData.size
}
package com.example.lastgym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MuscleAdapter(
    private val muscles: List<String>,
    private val onMuscleClick: (String) -> Unit
) : RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>() {

    class MuscleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val muscleName: TextView = itemView.findViewById(R.id.muscle_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_muscle, parent, false)
        return MuscleViewHolder(view)
    }

    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        val muscle = muscles[position]
        holder.muscleName.text = muscle
        holder.itemView.setOnClickListener {
            onMuscleClick(muscle)
        }
    }

    override fun getItemCount(): Int = muscles.size
}
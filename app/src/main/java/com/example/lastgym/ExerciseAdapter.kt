package com.example.lastgym


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(
    private val exercises: List<String>,
    private val onExerciseClick: (String) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_name)
        val exerciseIcon: ImageView = itemView.findViewById(R.id.exercise_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.exerciseName.text = exercise
        
        // Use ExerciseDrawables utility to load exercise image
        val resourceId = ExerciseDrawables.getDrawableResourceId(exercise, holder.itemView.context)
        if (resourceId != 0) {
            holder.exerciseIcon.setImageResource(resourceId)
        } else {
            // Use default icon if exercise image not found
            holder.exerciseIcon.setImageResource(android.R.drawable.ic_media_play)
        }
        
        holder.itemView.setOnClickListener {
            onExerciseClick(exercise)
        }
    }

    override fun getItemCount(): Int = exercises.size
}
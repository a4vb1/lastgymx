package com.example.lastgym

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExercisesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        // الحصول على اسم العضلة من النشاط السابق
        val muscleName = intent.getStringExtra("MUSCLE_NAME")
        supportActionBar?.title = "تمارين $muscleName"

        // قائمة التمارين لكل عضلة
        val exercises = when (muscleName) {
            "الظهر (Back)" -> listOf("Deadlift", "Pull-ups", "T-Bar Row")
            "الصدر (Chest)" -> listOf("Bench Press", "Incline Dumbbell Press", "Push-ups")
            "الأرجل (Legs)" -> listOf("Squat", "Leg Press", "Lunges")
            "الأكتاف (Shoulders)" -> listOf("Overhead Press", "Lateral Raise", "Front Raise")
            "البايسبس (Biceps)" -> listOf("Barbell Curl", "Hammer Curl", "Concentration Curl")
            "الترابيزوس (Trapezius)" -> listOf("Barbell Shrugs", "Dumbbell Shrugs", "Upright Row")
            "السواعد (Forearms)" -> listOf("Wrist Curl", "Reverse Curl", "Farmer's Carry")
            else -> emptyList()
        }

        // إعداد RecyclerView لعرض التمارين
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_exercises)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ExerciseAdapter(exercises) { exercise ->
            // الانتقال إلى صفحة تفاصيل التمارين عند اختيار تمرين
            val intent = Intent(this, ExerciseDetailsActivity::class.java)
            intent.putExtra("EXERCISE_NAME", exercise)
            startActivity(intent)
        }
    }
}
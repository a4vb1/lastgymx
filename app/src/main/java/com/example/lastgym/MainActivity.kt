package com.example.lastgym


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // قائمة العضلات الرئيسية
        val muscles = listOf(
            "الظهر (Back)",
            "الصدر (Chest)",
            "الأرجل (Legs)",
            "الأكتاف (Shoulders)",
            "البايسبس (Biceps)",
            "الترايسبس (Triceps)",
            "السواعد (Forearms)"
        )

        // إعداد RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MuscleAdapter(muscles) { muscle ->
            // الانتقال إلى صفحة التمارين عند اختيار العضلة
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("MUSCLE_NAME", muscle)
            startActivity(intent)
        }
        
        // إضافة إحصائيات بسيطة
        displayWorkoutStats()
    }
    
    private fun displayWorkoutStats() {
        val dbHelper = DatabaseHelper(this)
        val totalWorkouts = dbHelper.getTotalWorkoutCount()
        val lastWorkoutDate = dbHelper.getLastWorkoutDate()
        
        // يمكن إضافة عرض هذه الإحصائيات في TextView إضافي في المستقبل
        // أو في منطقة مخصصة للإحصائيات
        supportActionBar?.subtitle = if (totalWorkouts > 0) {
            "إجمالي التمارين: $totalWorkouts"
        } else {
            "ابدأ أول تمرين لك!"
        }
    }
}
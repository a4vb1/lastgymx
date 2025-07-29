package com.example.lastgym

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ExerciseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_details)

        // الحصول على اسم التمرين من النشاط السابق
        val exerciseName = intent.getStringExtra("EXERCISE_NAME")
        supportActionBar?.title = "تفاصيل التمرين: $exerciseName"

        // هنا يتم عرض تفاصيل التمرين وإمكانية تعديل البيانات وحفظها
        // بإمكانك إضافة منطق لقراءة البيانات من قاعدة البيانات أو تحديثها
    }
}
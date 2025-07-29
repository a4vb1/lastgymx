package com.example.lastgym

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ExerciseDetailsActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var selectedDate: String
    private lateinit var exerciseName: String
    private lateinit var historyAdapter: ExerciseHistoryAdapter
    private lateinit var historyRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_details)

        // تهيئة قاعدة البيانات
        databaseHelper = DatabaseHelper(this)

        // الحصول على اسم التمرين من النشاط السابق
        exerciseName = intent.getStringExtra("EXERCISE_NAME") ?: "تمرين غير معروف"
        supportActionBar?.title = "تفاصيل التمرين: $exerciseName"

        // تعيين التاريخ الافتراضي إلى اليوم
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        selectedDate = dateFormat.format(calendar.time)

        // إعداد العناصر
        setupViews()
        setupDatePicker()
        setupHistoryRecyclerView()
        loadExerciseHistory()
    }

    private fun setupViews() {
        val exerciseTitle: TextView = findViewById(R.id.exercise_title)
        val exerciseDescription: TextView = findViewById(R.id.exercise_description)
        val exerciseImage: ImageView = findViewById(R.id.exercise_image)
        val saveButton: Button = findViewById(R.id.save_button)

        exerciseTitle.text = exerciseName
        
        // تعيين الوصف الحقيقي للتمرين باستخدام ExerciseDescriptions
        exerciseDescription.text = ExerciseDescriptions.getDescription(exerciseName)

        // إعداد زر الحفظ
        saveButton.setOnClickListener {
            saveExerciseData()
        }
    }

    private fun setupDatePicker() {
        val datePickerButton: Button = findViewById(R.id.date_picker_button)
        datePickerButton.text = "التاريخ: $selectedDate"

        datePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                datePickerButton.text = "التاريخ: $selectedDate"
            }, year, month, day).show()
        }
    }

    private fun setupHistoryRecyclerView() {
        historyRecyclerView = findViewById(R.id.history_recycler_view)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadExerciseHistory() {
        val historyData = databaseHelper.getExerciseData(exerciseName)
        historyAdapter = ExerciseHistoryAdapter(historyData)
        historyRecyclerView.adapter = historyAdapter
    }

    private fun saveExerciseData() {
        val weightInput: EditText = findViewById(R.id.weight_input)
        val repetitionsInput: EditText = findViewById(R.id.repetitions_input)
        val setsInput: EditText = findViewById(R.id.sets_input)

        val weightText = weightInput.text.toString().trim()
        val repetitionsText = repetitionsInput.text.toString().trim()
        val setsText = setsInput.text.toString().trim()

        // التحقق من صحة البيانات
        if (weightText.isEmpty() || repetitionsText.isEmpty() || setsText.isEmpty()) {
            Toast.makeText(this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val weight = weightText.toDouble()
            val repetitions = repetitionsText.toInt()
            val sets = setsText.toInt()

            if (weight <= 0 || repetitions <= 0 || sets <= 0) {
                Toast.makeText(this, "يرجى إدخال قيم صحيحة أكبر من الصفر", Toast.LENGTH_SHORT).show()
                return
            }

            // حفظ البيانات في قاعدة البيانات
            val result = databaseHelper.saveExerciseData(exerciseName, weight, repetitions, sets, selectedDate)
            
            if (result != -1L) {
                Toast.makeText(this, "تم حفظ البيانات بنجاح", Toast.LENGTH_SHORT).show()
                
                // مسح النماذج
                weightInput.text.clear()
                repetitionsInput.text.clear()
                setsInput.text.clear()
                
                // تحديث السجل
                loadExerciseHistory()
            } else {
                Toast.makeText(this, "حدث خطأ أثناء حفظ البيانات", Toast.LENGTH_SHORT).show()
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "يرجى إدخال أرقام صحيحة", Toast.LENGTH_SHORT).show()
        }
    }
}
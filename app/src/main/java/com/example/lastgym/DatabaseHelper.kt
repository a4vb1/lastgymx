package com.example.lastgym

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "FitnessApp.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "exercise_data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EXERCISE_NAME = "exercise_name"
        private const val COLUMN_WEIGHT = "weight"
        private const val COLUMN_REPETITIONS = "repetitions"
        private const val COLUMN_SETS = "sets"
        private const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EXERCISE_NAME TEXT,
                $COLUMN_WEIGHT REAL,
                $COLUMN_REPETITIONS INTEGER,
                $COLUMN_SETS INTEGER,
                $COLUMN_DATE TEXT
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun saveExerciseData(
        exerciseName: String,
        weight: Double,
        repetitions: Int,
        sets: Int,
        date: String
    ): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EXERCISE_NAME, exerciseName)
            put(COLUMN_WEIGHT, weight)
            put(COLUMN_REPETITIONS, repetitions)
            put(COLUMN_SETS, sets)
            put(COLUMN_DATE, date)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getExerciseData(exerciseName: String): List<Map<String, Any>> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_EXERCISE_NAME, COLUMN_WEIGHT, COLUMN_REPETITIONS, COLUMN_SETS, COLUMN_DATE),
            "$COLUMN_EXERCISE_NAME = ?",
            arrayOf(exerciseName),
            null,
            null,
            "$COLUMN_DATE DESC"
        )

        // تحقق من الأعمدة
        val columnIndexWeight = cursor.getColumnIndex(COLUMN_WEIGHT)
        val columnIndexRepetitions = cursor.getColumnIndex(COLUMN_REPETITIONS)
        val columnIndexSets = cursor.getColumnIndex(COLUMN_SETS)
        val columnIndexDate = cursor.getColumnIndex(COLUMN_DATE)

        if (columnIndexWeight == -1 || columnIndexRepetitions == -1 || columnIndexSets == -1 || columnIndexDate == -1) {
            throw IllegalStateException("One or more columns are missing in the database query.")
        }

        val data = mutableListOf<Map<String, Any>>()
        while (cursor.moveToNext()) {
            val row = mapOf(
                COLUMN_WEIGHT to cursor.getDouble(columnIndexWeight),
                COLUMN_REPETITIONS to cursor.getInt(columnIndexRepetitions),
                COLUMN_SETS to cursor.getInt(columnIndexSets),
                COLUMN_DATE to cursor.getString(columnIndexDate)
            )
            data.add(row)
        }
        cursor.close()
        return data
    }

    fun getTotalWorkoutCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        var count = 0
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }
        cursor.close()
        return count
    }

    fun getLastWorkoutDate(): String? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_DATE),
            null,
            null,
            null,
            null,
            "$COLUMN_DATE DESC",
            "1"
        )
        
        var lastDate: String? = null
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COLUMN_DATE)
            if (columnIndex != -1) {
                lastDate = cursor.getString(columnIndex)
            }
        }
        cursor.close()
        return lastDate
    }
}
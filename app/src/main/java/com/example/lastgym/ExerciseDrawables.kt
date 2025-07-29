package com.example.lastgym

/**
 * Utility object for managing drawable paths and resources for workout exercise images.
 * Each exercise has its respective image file with the format 'exercise_name.jpg'
 * stored in the drawable directory.
 */
object ExerciseDrawables {
    
    /**
     * Maps exercise names to their corresponding drawable resource names.
     * The resource names follow Android naming conventions (lowercase, underscores).
     */
    private val exerciseResourceMap = mapOf(
        // 💪 الظهر (Back)
        "Deadlift" to "deadlift",
        "Pull-ups" to "pull_ups", 
        "T-Bar Row" to "t_bar_row",
        
        // 🏋️ الصدر (Chest)
        "Bench Press" to "bench_press",
        "Incline Dumbbell Press" to "incline_dumbbell_press",
        "Push-ups" to "push_ups",
        
        // 🦵 الأرجل (Legs)
        "Squat" to "squat",
        "Leg Press" to "leg_press", 
        "Lunges" to "lunges",
        
        // 🔺 الأكتاف (Shoulders)
        "Overhead Press" to "overhead_press",
        "Lateral Raise" to "lateral_raise",
        "Front Raise" to "front_raise",
        
        // 💪 البايسبس (Biceps)
        "Barbell Curl" to "barbell_curl",
        "Hammer Curl" to "hammer_curl",
        "Concentration Curl" to "concentration_curl",
        
        // 💪 الترايسبس (Triceps)  
        "Triceps Pushdown" to "triceps_pushdown",
        "Skull Crushers" to "skull_crushers",
        "Dips" to "dips",
        
        // 🤏 السواعد (Forearms)
        "Wrist Curl" to "wrist_curl",
        "Reverse Curl" to "reverse_curl",
        "Farmer's Carry" to "farmers_carry"
    )
    
    /**
     * Gets the drawable resource name for a given exercise.
     * @param exerciseName The name of the exercise
     * @return The drawable resource name, or null if not found
     */
    fun getDrawableResourceName(exerciseName: String): String? {
        return exerciseResourceMap[exerciseName]
    }
    
    /**
     * Gets the drawable resource ID for a given exercise using Android's resource identifier.
     * @param exerciseName The name of the exercise
     * @param context Android context to access resources
     * @return The drawable resource ID, or 0 if not found
     */
    fun getDrawableResourceId(exerciseName: String, context: android.content.Context): Int {
        val resourceName = exerciseResourceMap[exerciseName] ?: return 0
        return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
    
    /**
     * Gets the expected file path for an exercise image in the drawable directory.
     * @param exerciseName The name of the exercise
     * @return The file path string in format "exercise_name.jpg", or null if exercise not found
     */
    fun getDrawableFilePath(exerciseName: String): String? {
        val resourceName = exerciseResourceMap[exerciseName] ?: return null
        return "$resourceName.jpg"
    }
    
    /**
     * Gets all available exercise names that have drawable mappings.
     * @return Set of all exercise names with drawable resources
     */
    fun getAllExerciseNames(): Set<String> {
        return exerciseResourceMap.keys
    }
    
    /**
     * Gets all drawable resource names for exercises.
     * @return Set of all drawable resource names
     */
    fun getAllDrawableResourceNames(): Set<String> {
        return exerciseResourceMap.values.toSet()
    }
    
    /**
     * Gets all expected file paths for exercise images.
     * @return List of all file paths in format "exercise_name.jpg"
     */
    fun getAllDrawableFilePaths(): List<String> {
        return exerciseResourceMap.values.map { "$it.jpg" }
    }
    
    /**
     * Checks if an exercise has a drawable resource mapping.
     * @param exerciseName The name of the exercise
     * @return true if the exercise has a drawable mapping, false otherwise
     */
    fun hasDrawableForExercise(exerciseName: String): Boolean {
        return exerciseResourceMap.containsKey(exerciseName)
    }
    
    /**
     * Gets a comprehensive mapping of exercises organized by muscle group with their drawable paths.
     * @return Map of muscle groups to their exercises with drawable file paths
     */
    fun getExercisesByMuscleGroupWithDrawables(): Map<String, List<Pair<String, String>>> {
        return mapOf(
            "الظهر (Back)" to listOf(
                "Deadlift" to "deadlift.jpg",
                "Pull-ups" to "pull_ups.jpg", 
                "T-Bar Row" to "t_bar_row.jpg"
            ),
            "الصدر (Chest)" to listOf(
                "Bench Press" to "bench_press.jpg",
                "Incline Dumbbell Press" to "incline_dumbbell_press.jpg",
                "Push-ups" to "push_ups.jpg"
            ),
            "الأرجل (Legs)" to listOf(
                "Squat" to "squat.jpg",
                "Leg Press" to "leg_press.jpg", 
                "Lunges" to "lunges.jpg"
            ),
            "الأكتاف (Shoulders)" to listOf(
                "Overhead Press" to "overhead_press.jpg",
                "Lateral Raise" to "lateral_raise.jpg",
                "Front Raise" to "front_raise.jpg"
            ),
            "البايسبس (Biceps)" to listOf(
                "Barbell Curl" to "barbell_curl.jpg",
                "Hammer Curl" to "hammer_curl.jpg",
                "Concentration Curl" to "concentration_curl.jpg"
            ),
            "الترايسبس (Triceps)" to listOf(
                "Triceps Pushdown" to "triceps_pushdown.jpg",
                "Skull Crushers" to "skull_crushers.jpg",
                "Dips" to "dips.jpg"
            ),
            "السواعد (Forearms)" to listOf(
                "Wrist Curl" to "wrist_curl.jpg",
                "Reverse Curl" to "reverse_curl.jpg",
                "Farmer's Carry" to "farmers_carry.jpg"
            )
        )
    }
}
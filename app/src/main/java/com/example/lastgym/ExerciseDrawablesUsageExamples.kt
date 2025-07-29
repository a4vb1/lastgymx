package com.example.lastgym

import android.content.Context
import android.widget.ImageView

/**
 * Example usage scenarios for the ExerciseDrawables utility class.
 * This demonstrates how to integrate the drawable paths system into different parts of the app.
 */
object ExerciseDrawablesUsageExamples {

    /**
     * Example 1: Load exercise image in an adapter (like ExerciseAdapter)
     */
    fun loadExerciseImageInAdapter(exerciseName: String, imageView: ImageView, context: Context) {
        val resourceId = ExerciseDrawables.getDrawableResourceId(exerciseName, context)
        if (resourceId != 0) {
            imageView.setImageResource(resourceId)
        } else {
            // Fallback to default icon
            imageView.setImageResource(android.R.drawable.ic_media_play)
        }
    }

    /**
     * Example 2: Get all exercise file paths for validation or preloading
     */
    fun getAllExerciseFilePaths(): List<String> {
        return ExerciseDrawables.getAllDrawableFilePaths()
    }

    /**
     * Example 3: Validate that an exercise has an associated image
     */
    fun validateExerciseHasImage(exerciseName: String): Boolean {
        return ExerciseDrawables.hasDrawableForExercise(exerciseName)
    }

    /**
     * Example 4: Get exercises by muscle group with their image paths
     * Useful for organizing UI sections
     */
    fun getExercisesForMuscleGroupDisplay(muscleGroup: String): List<Pair<String, String>>? {
        val exercisesByGroup = ExerciseDrawables.getExercisesByMuscleGroupWithDrawables()
        return exercisesByGroup[muscleGroup]
    }

    /**
     * Example 5: Create a mapping for dynamic exercise loading
     */
    fun createExerciseImageMapping(context: Context): Map<String, Int> {
        val allExercises = ExerciseDrawables.getAllExerciseNames()
        val mapping = mutableMapOf<String, Int>()
        
        allExercises.forEach { exercise ->
            val resourceId = ExerciseDrawables.getDrawableResourceId(exercise, context)
            if (resourceId != 0) {
                mapping[exercise] = resourceId
            }
        }
        
        return mapping
    }

    /**
     * Example 6: Batch load exercise images for caching
     */
    fun preloadExerciseImages(context: Context): Map<String, String> {
        val allExercises = ExerciseDrawables.getAllExerciseNames()
        val imageCache = mutableMapOf<String, String>()
        
        allExercises.forEach { exercise ->
            val filePath = ExerciseDrawables.getDrawableFilePath(exercise)
            if (filePath != null) {
                imageCache[exercise] = filePath
            }
        }
        
        return imageCache
    }

    /**
     * Example 7: Filter exercises that have images available
     */
    fun getExercisesWithImages(allExercises: List<String>): List<String> {
        return allExercises.filter { exercise ->
            ExerciseDrawables.hasDrawableForExercise(exercise)
        }
    }

    /**
     * Example 8: Get resource name for file system operations
     */
    fun getExerciseResourceName(exerciseName: String): String? {
        return ExerciseDrawables.getDrawableResourceName(exerciseName)
    }

    /**
     * Example 9: Debug function to list all mappings
     */
    fun debugPrintAllMappings() {
        val allExercises = ExerciseDrawables.getAllExerciseNames()
        println("=== Exercise to Drawable Mapping ===")
        allExercises.forEach { exercise ->
            val resourceName = ExerciseDrawables.getDrawableResourceName(exercise)
            val filePath = ExerciseDrawables.getDrawableFilePath(exercise)
            println("$exercise -> $resourceName -> $filePath")
        }
    }

    /**
     * Example 10: Integration with ExerciseDetailsActivity
     */
    fun setupExerciseDetailsImage(exerciseName: String, imageView: ImageView, context: Context) {
        // First try to load the specific exercise image
        val resourceId = ExerciseDrawables.getDrawableResourceId(exerciseName, context)
        if (resourceId != 0) {
            imageView.setImageResource(resourceId)
            imageView.contentDescription = "Image for $exerciseName exercise"
        } else {
            // Use placeholder and log the missing image
            imageView.setImageResource(android.R.drawable.ic_menu_gallery)
            imageView.contentDescription = "Placeholder image for $exerciseName exercise"
            println("Warning: No image found for exercise: $exerciseName")
        }
    }
}
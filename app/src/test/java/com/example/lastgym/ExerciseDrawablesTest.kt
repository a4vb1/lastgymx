package com.example.lastgym

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for ExerciseDrawables utility class
 */
class ExerciseDrawablesTest {

    @Test
    fun `test getDrawableResourceName returns correct resource name for valid exercise`() {
        assertEquals("bench_press", ExerciseDrawables.getDrawableResourceName("Bench Press"))
        assertEquals("pull_ups", ExerciseDrawables.getDrawableResourceName("Pull-ups"))
        assertEquals("t_bar_row", ExerciseDrawables.getDrawableResourceName("T-Bar Row"))
        assertEquals("farmers_carry", ExerciseDrawables.getDrawableResourceName("Farmer's Carry"))
    }

    @Test
    fun `test getDrawableResourceName returns null for invalid exercise`() {
        assertNull(ExerciseDrawables.getDrawableResourceName("Invalid Exercise"))
        assertNull(ExerciseDrawables.getDrawableResourceName(""))
        assertNull(ExerciseDrawables.getDrawableResourceName("Non-existent"))
    }

    @Test
    fun `test getDrawableFilePath returns correct file path for valid exercise`() {
        assertEquals("deadlift.jpg", ExerciseDrawables.getDrawableFilePath("Deadlift"))
        assertEquals("squat.jpg", ExerciseDrawables.getDrawableFilePath("Squat"))
        assertEquals("incline_dumbbell_press.jpg", ExerciseDrawables.getDrawableFilePath("Incline Dumbbell Press"))
    }

    @Test
    fun `test getDrawableFilePath returns null for invalid exercise`() {
        assertNull(ExerciseDrawables.getDrawableFilePath("Invalid Exercise"))
        assertNull(ExerciseDrawables.getDrawableFilePath(""))
    }

    @Test
    fun `test getAllExerciseNames returns correct count of exercises`() {
        val exerciseNames = ExerciseDrawables.getAllExerciseNames()
        assertEquals(21, exerciseNames.size)
        
        // Test that it contains exercises from each muscle group
        assertTrue(exerciseNames.contains("Deadlift"))
        assertTrue(exerciseNames.contains("Bench Press"))
        assertTrue(exerciseNames.contains("Squat"))
        assertTrue(exerciseNames.contains("Overhead Press"))
        assertTrue(exerciseNames.contains("Barbell Curl"))
        assertTrue(exerciseNames.contains("Triceps Pushdown"))
        assertTrue(exerciseNames.contains("Wrist Curl"))
    }

    @Test
    fun `test getAllDrawableResourceNames returns correct count and format`() {
        val resourceNames = ExerciseDrawables.getAllDrawableResourceNames()
        assertEquals(21, resourceNames.size)
        
        // Test resource names follow correct format (lowercase, underscores)
        assertTrue(resourceNames.contains("deadlift"))
        assertTrue(resourceNames.contains("bench_press"))
        assertTrue(resourceNames.contains("pull_ups"))
        assertTrue(resourceNames.contains("farmers_carry"))
        
        // Ensure no uppercase or spaces in resource names
        resourceNames.forEach { name ->
            assertFalse("Resource name should be lowercase", name.any { it.isUpperCase() })
            assertFalse("Resource name should not contain spaces", name.contains(" "))
        }
    }

    @Test
    fun `test getAllDrawableFilePaths returns correct count and format`() {
        val filePaths = ExerciseDrawables.getAllDrawableFilePaths()
        assertEquals(21, filePaths.size)
        
        // Test all file paths end with .jpg
        filePaths.forEach { path ->
            assertTrue("File path should end with .jpg", path.endsWith(".jpg"))
        }
        
        // Test specific file paths
        assertTrue(filePaths.contains("deadlift.jpg"))
        assertTrue(filePaths.contains("bench_press.jpg"))
        assertTrue(filePaths.contains("farmers_carry.jpg"))
    }

    @Test
    fun `test hasDrawableForExercise returns correct boolean values`() {
        // Valid exercises should return true
        assertTrue(ExerciseDrawables.hasDrawableForExercise("Deadlift"))
        assertTrue(ExerciseDrawables.hasDrawableForExercise("Bench Press"))
        assertTrue(ExerciseDrawables.hasDrawableForExercise("Squat"))
        
        // Invalid exercises should return false
        assertFalse(ExerciseDrawables.hasDrawableForExercise("Invalid Exercise"))
        assertFalse(ExerciseDrawables.hasDrawableForExercise(""))
        assertFalse(ExerciseDrawables.hasDrawableForExercise("Non-existent"))
    }

    @Test
    fun `test getExercisesByMuscleGroupWithDrawables returns correct structure`() {
        val exercisesByGroup = ExerciseDrawables.getExercisesByMuscleGroupWithDrawables()
        
        // Test correct number of muscle groups
        assertEquals(7, exercisesByGroup.size)
        
        // Test muscle groups exist
        assertTrue(exercisesByGroup.containsKey("الظهر (Back)"))
        assertTrue(exercisesByGroup.containsKey("الصدر (Chest)"))
        assertTrue(exercisesByGroup.containsKey("الأرجل (Legs)"))
        assertTrue(exercisesByGroup.containsKey("الأكتاف (Shoulders)"))
        assertTrue(exercisesByGroup.containsKey("البايسبس (Biceps)"))
        assertTrue(exercisesByGroup.containsKey("الترايسبس (Triceps)"))
        assertTrue(exercisesByGroup.containsKey("السواعد (Forearms)"))
        
        // Test each muscle group has 3 exercises
        exercisesByGroup.values.forEach { exercises ->
            assertEquals(3, exercises.size)
        }
        
        // Test structure of returned pairs (exercise name, file path)
        val backExercises = exercisesByGroup["الظهر (Back)"]!!
        assertTrue(backExercises.contains("Deadlift" to "deadlift.jpg"))
        assertTrue(backExercises.contains("Pull-ups" to "pull_ups.jpg"))
        assertTrue(backExercises.contains("T-Bar Row" to "t_bar_row.jpg"))
    }

    @Test
    fun `test consistency between different methods`() {
        val allExerciseNames = ExerciseDrawables.getAllExerciseNames()
        
        // Every exercise name should have a drawable resource name
        allExerciseNames.forEach { exerciseName ->
            assertNotNull("Exercise $exerciseName should have a resource name", 
                         ExerciseDrawables.getDrawableResourceName(exerciseName))
        }
        
        // Every exercise name should have a file path
        allExerciseNames.forEach { exerciseName ->
            assertNotNull("Exercise $exerciseName should have a file path", 
                         ExerciseDrawables.getDrawableFilePath(exerciseName))
        }
        
        // Every exercise name should return true for hasDrawableForExercise
        allExerciseNames.forEach { exerciseName ->
            assertTrue("Exercise $exerciseName should have drawable", 
                      ExerciseDrawables.hasDrawableForExercise(exerciseName))
        }
    }
}
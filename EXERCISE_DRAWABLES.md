# Exercise Drawable Paths Organization

This document outlines the organized system for managing workout exercise images in the LastGym Android application.

## Overview

The `ExerciseDrawables` utility class provides a centralized system for managing drawable paths and resources for workout exercise images. Each exercise has its respective image file with the format `exercise_name.jpg` stored in the drawable directory.

## File Naming Convention

All exercise image files follow the pattern: `exercise_name.jpg`

Where `exercise_name` is the exercise name converted to Android resource naming conventions:
- Lowercase letters only
- Spaces replaced with underscores
- No special characters or punctuation

## Exercise Image Files

The following 21 exercise images should be placed in `app/src/main/res/drawable/`:

### Back Exercises (الظهر)
- `deadlift.jpg` - Deadlift exercise
- `pull_ups.jpg` - Pull-ups exercise  
- `t_bar_row.jpg` - T-Bar Row exercise

### Chest Exercises (الصدر)
- `bench_press.jpg` - Bench Press exercise
- `incline_dumbbell_press.jpg` - Incline Dumbbell Press exercise
- `push_ups.jpg` - Push-ups exercise

### Leg Exercises (الأرجل)
- `squat.jpg` - Squat exercise
- `leg_press.jpg` - Leg Press exercise
- `lunges.jpg` - Lunges exercise

### Shoulder Exercises (الأكتاف)
- `overhead_press.jpg` - Overhead Press exercise
- `lateral_raise.jpg` - Lateral Raise exercise
- `front_raise.jpg` - Front Raise exercise

### Biceps Exercises (البايسبس)
- `barbell_curl.jpg` - Barbell Curl exercise
- `hammer_curl.jpg` - Hammer Curl exercise
- `concentration_curl.jpg` - Concentration Curl exercise

### Triceps Exercises (الترايسبس)
- `triceps_pushdown.jpg` - Triceps Pushdown exercise
- `skull_crushers.jpg` - Skull Crushers exercise
- `dips.jpg` - Dips exercise

### Forearm Exercises (السواعد)
- `wrist_curl.jpg` - Wrist Curl exercise
- `reverse_curl.jpg` - Reverse Curl exercise
- `farmers_carry.jpg` - Farmer's Carry exercise

## Usage

### Getting Drawable Resource Name
```kotlin
val resourceName = ExerciseDrawables.getDrawableResourceName("Bench Press")
// Returns: "bench_press"
```

### Getting Drawable Resource ID
```kotlin
val resourceId = ExerciseDrawables.getDrawableResourceId("Squat", context)
// Returns: R.drawable.squat resource ID
```

### Getting File Path
```kotlin
val filePath = ExerciseDrawables.getDrawableFilePath("Pull-ups")
// Returns: "pull_ups.jpg"
```

### Getting All Exercise Names
```kotlin
val allExercises = ExerciseDrawables.getAllExerciseNames()
// Returns: Set of all 21 exercise names
```

### Getting Exercises by Muscle Group with Drawables
```kotlin
val exercisesByGroup = ExerciseDrawables.getExercisesByMuscleGroupWithDrawables()
// Returns: Map of muscle groups to exercise-filepath pairs
```

### Checking if Exercise Has Drawable
```kotlin
val hasDrawable = ExerciseDrawables.hasDrawableForExercise("Deadlift")
// Returns: true if exercise has drawable mapping
```

## Integration with Existing Code

The `ExerciseDrawables` class is designed to integrate seamlessly with:
- `ExercisesActivity.kt` - For displaying exercise images in the exercise list
- `ExerciseDetailsActivity.kt` - For showing exercise images in detail views
- `ExerciseAdapter.kt` - For loading images in RecyclerView adapters

## Example Integration

```kotlin
// In ExerciseAdapter or similar class
class ExerciseAdapter(private val exercises: List<String>) : RecyclerView.Adapter<ExerciseViewHolder>() {
    
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        
        // Load exercise image using the drawable system
        val resourceId = ExerciseDrawables.getDrawableResourceId(exercise, holder.itemView.context)
        if (resourceId != 0) {
            holder.exerciseImage.setImageResource(resourceId)
        } else {
            // Use placeholder image if exercise image not found
            holder.exerciseImage.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }
}
```

## Directory Structure

```
app/src/main/res/drawable/
├── ic_launcher_background.xml
├── ic_launcher_foreground.xml
├── deadlift.jpg
├── pull_ups.jpg
├── t_bar_row.jpg
├── bench_press.jpg
├── incline_dumbbell_press.jpg
├── push_ups.jpg
├── squat.jpg
├── leg_press.jpg
├── lunges.jpg
├── overhead_press.jpg
├── lateral_raise.jpg
├── front_raise.jpg
├── barbell_curl.jpg
├── hammer_curl.jpg
├── concentration_curl.jpg
├── triceps_pushdown.jpg
├── skull_crushers.jpg
├── dips.jpg
├── wrist_curl.jpg
├── reverse_curl.jpg
└── farmers_carry.jpg
```

## Notes

- All image files should be optimized for mobile devices (recommended size: 500x500px or smaller)
- Use JPEG format for photographs, PNG for graphics with transparency
- Consider providing multiple densities (hdpi, xhdpi, xxhdpi) for better display quality
- The system is extensible - new exercises can be easily added to the `exerciseResourceMap`
package com.example.lastgym

/**
 * Utility object containing detailed Arabic descriptions for all exercises
 * organized by muscle group
 */
object ExerciseDescriptions {
    
    private val exerciseDescriptions = mapOf(
        // 💪 الظهر (Back)
        "Deadlift" to "تمرين مركّز يقوّي عضلات أسفل الظهر، والأرداف، والهامسترنغ. يتم برفع الوزن من الأرض حتى الوقوف الكامل.",
        "Pull-ups" to "تمرين وزن الجسم يستهدف عضلات الظهر العلوية والذراعين، يتم بسحب الجسم للأعلى باستخدام عارضة.",
        "T-Bar Row" to "تمرين مركّز على وسط الظهر، يتم باستخدام آلة T-Bar أو بار مثبت وسحب الوزن نحو الجسم مع انحناء الظهر قليلاً.",
        
        // 🏋️ الصدر (Chest)
        "Bench Press" to "تمرين أساسي للصدر يتم عبر رفع بار حديد أثناء الاستلقاء على مقعد مسطح.",
        "Incline Dumbbell Press" to "تمرين يستهدف الجزء العلوي من الصدر باستخدام الدمبل أثناء الجلوس على مقعد مائل.",
        "Push-ups" to "تمرين وزن الجسم يقوّي عضلات الصدر والذراعين، يتم بدفع الجسم لأعلى أثناء الاستلقاء على الأرض.",
        
        // 🦵 الأرجل (Legs)
        "Squat" to "تمرين مركزي يعمل على الفخذين، المؤخرة، وأسفل الظهر، يتم عبر النزول والرجوع لوضع الوقوف.",
        "Leg Press" to "تمرين باستخدام آلة يتم فيه دفع منصة الوزن بعيدًا عن الجسم باستخدام الأرجل.",
        "Lunges" to "تمرين يستهدف الفخذين والمؤخرة، يتم بخطوة طويلة للأمام وثني الركبة ثم العودة.",
        
        // 🔺 الأكتاف (Shoulders)
        "Overhead Press" to "تمرين يُقوّي عضلات الكتف الأمامية باستخدام بار أو دمبل، يتم برفع الوزن فوق الرأس.",
        "Lateral Raise" to "تمرين يستهدف عضلات الكتف الجانبية، يتم برفع الدمبلز إلى الجانبين حتى ارتفاع الكتف.",
        "Front Raise" to "تمرين للكتف الأمامي، يتم برفع الدمبلز أو بار خفيف أمام الجسم حتى مستوى الكتف.",
        
        // 💪 البايسبس (Biceps)
        "Barbell Curl" to "تمرين مركّز على البايسبس باستخدام بار يتم رفعه للأعلى بثني المرفقين.",
        "Hammer Curl" to "يشبه تمرين الكيرل، ولكن بإمساك الدمبلز بشكل طولي (مثل المطرقة)، يعمل على البايسبس والسواعد.",
        "Concentration Curl" to "تمرين يجلس فيه المتدرّب ويقوم برفع دمبل واحد بذراع واحدة ببطء لعزل عضلة البايسبس.",
        
        // 💪 الترايسبس (Triceps)
        "Triceps Pushdown" to "تمرين باستخدام كابل يتم فيه دفع المقبض للأسفل لتقوية الترايسبس.",
        "Skull Crushers" to "تمرين يتم أثناء الاستلقاء باستخدام بار EZ، يتم خفض الوزن تجاه الجبهة ثم رفعه.",
        "Dips" to "تمرين وزن الجسم يتم بخفض الجسم بين عارضتين ثم دفعه لأعلى، يقوّي الترايسبس والصدر.",
        
        // 🤏 السواعد (Forearms)
        "Wrist Curl" to "تمرين للسواعد الأمامية، يتم بحمل دمبل أو بار وثني المعصم للأعلى أثناء الجلوس.",
        "Reverse Curl" to "يشبه تمرين البار العادي لكن مع قبضة معكوسة (راحة اليد للأسفل)، يركّز على الساعد.",
        "Farmer's Carry" to "تمرين بسيط وفعّال، يتم بحمل أوزان ثقيلة بكلتا اليدين والمشي لمسافة معينة لتقوية القبضة والسواعد."
    )
    
    /**
     * Get the description for a specific exercise
     * @param exerciseName The name of the exercise
     * @return The description in Arabic, or a default message if not found
     */
    fun getDescription(exerciseName: String): String {
        return exerciseDescriptions[exerciseName] ?: "وصف هذا التمرين غير متوفر حاليًا."
    }
    
    /**
     * Check if an exercise has a description available
     * @param exerciseName The name of the exercise
     * @return true if description exists, false otherwise
     */
    fun hasDescription(exerciseName: String): Boolean {
        return exerciseDescriptions.containsKey(exerciseName)
    }
}
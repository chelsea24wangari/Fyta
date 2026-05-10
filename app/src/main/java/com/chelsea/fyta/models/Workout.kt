package com.chelsea.fyta.models

data class Workout(
    var id: String = "",
    var name: String = "",
    var duration: String = "",
    var calories: String = "",
    var date: Long = System.currentTimeMillis(),
    var sets: List<WorkoutSet> = emptyList()
)

data class WorkoutSet(
    var weight: String = "",
    var reps: String = ""
)

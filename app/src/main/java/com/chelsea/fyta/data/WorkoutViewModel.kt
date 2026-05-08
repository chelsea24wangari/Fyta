package com.chelsea.fyta.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.chelsea.fyta.models.Workout
import com.chelsea.fyta.ui.navigations.ROUT_WORKOUTS
import com.google.firebase.database.*

class WorkoutViewModel() : ViewModel() {

    // Initialize workouts with a Compose state list
    var workouts by mutableStateOf<List<Workout>>(emptyList())
        private set

    private val database = FirebaseDatabase.getInstance()
    private val workoutsRef = database.getReference("Workouts")

    //  ADD WORKOUT
    fun addWorkout(name: String, duration: String, description: String, context: Context, navController: NavController) {
        val workoutId = workoutsRef.push().key ?: return

        val workout = Workout(
            id = workoutId,
            name = name,
            duration = duration,
            description = description
        )

        workoutsRef.child(workoutId).setValue(workout)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Workout added", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_WORKOUTS)
                } else {
                    Toast.makeText(context, it.exception?.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 🔹 FETCH WORKOUTS
    fun fetchWorkouts(context: Context) {
        workoutsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val workoutList = mutableListOf<Workout>()
                for (snap in snapshot.children) {
                    val workout = snap.getValue(Workout::class.java)
                    workout?.let { workoutList.add(it) }
                }
                workouts = workoutList
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    //  UPDATE WORKOUT
    fun updateWorkout(
        id: String,
        name: String,
        duration: String,
        description: String,
        context: Context
    ) {
        val updatedWorkout = Workout(id, name, duration, description)

        workoutsRef.child(id).setValue(updatedWorkout)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Workout updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, it.exception?.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //  DELETE WORKOUT
    fun deleteWorkout(id: String, context: Context) {
        workoutsRef.child(id).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Workout deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, it.exception?.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
package com.marcosnarvaez.android.testshoppingapp.products

import android.app.Application
import android.content.Context
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class FetchDataLogin @Inject constructor(private val application: Application) {

    sealed class Result {
        data class Success(val isLogged: Boolean) : Result()
        object Failure : Result()
    }

    private val sharedPreferences = application.getSharedPreferences("data", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun logIn(isLogged: Boolean): Result {
        return try {
            editor.putBoolean("isLogged", isLogged)
            editor.apply()
            Result.Success(true)
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                Result.Failure
            } else {
                throw t
            }
        }
    }

    fun checkedLogIn(): Boolean {
        return try {
            return sharedPreferences.getBoolean("isLogged", false)
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                false
            } else {
                throw t
            }
        }
    }
}

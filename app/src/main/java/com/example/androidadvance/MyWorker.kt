package com.example.androidadvance


import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.Data

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val RESULT_KEY = "result_key"
    }

    override fun doWork(): Result {
        try {
            val resultData = "Pekerjaan Selesai pada ${System.currentTimeMillis()}"

            val outputData = Data.Builder()
                .putString(RESULT_KEY, resultData)
                .build()

            return Result.success(outputData)
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}


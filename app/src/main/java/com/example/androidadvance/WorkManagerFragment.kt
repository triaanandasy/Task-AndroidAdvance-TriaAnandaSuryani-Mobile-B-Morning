package com.example.androidadvance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkManagerFragment : Fragment() {

    private lateinit var textResult: TextView
    private lateinit var btnStartWork: Button
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_work_manager, container, false)

        imageView = view.findViewById(R.id.back)
        textResult = view.findViewById(R.id.textResult)
        btnStartWork = view.findViewById(R.id.btnStartWork)

        btnStartWork.setOnClickListener {
            startWork()
        }

        return view
    }

    private fun startWork() {
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(requireContext()).enqueue(workRequest)

        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(workRequest.id)
            .observe(viewLifecycleOwner, Observer { workInfo ->
                if (workInfo != null) {
                    when (workInfo.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            val result = workInfo.outputData.getString(MyWorker.RESULT_KEY)
                            textResult.text = "Hasil Pekerjaan: $result"
                        }
                        WorkInfo.State.FAILED -> {
                            textResult.text = "Pekerjaan Gagal"
                        }
                        WorkInfo.State.CANCELLED -> {
                            textResult.text = "Pekerjaan Dibatalkan"
                        }
                        else -> {
                            textResult.text = "Pekerjaan Sedang Berlangsung"
                        }
                    }
                }
            })
        imageView.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}

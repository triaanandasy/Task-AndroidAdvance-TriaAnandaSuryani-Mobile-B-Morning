package com.example.androidadvance

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.Calendar

class AlarmManagerFragment : Fragment() {
    private lateinit var timePicker: TimePicker
    private lateinit var pendingIntent: PendingIntent
    private lateinit var button: Button
    private var repeatCheckbox: CheckBox? = null
    private var setAlarmButton: Button? = null

    private var alarmManager: AlarmManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alarm_manager, container, false)

        timePicker = view.findViewById(R.id.timePicker)
        repeatCheckbox = view.findViewById(R.id.repeatCheckbox)
        setAlarmButton = view.findViewById(R.id.setAlarmButton)
        button = view.findViewById(R.id.back)

        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        setAlarmButton?.setOnClickListener {
            setAlarm()
            Toast.makeText(
                requireContext(),
                "Atur alarm berhasil ${timePicker.hour}:${timePicker.minute}",
                Toast.LENGTH_LONG
            ).show()
        }

        return view
    }

    private fun setAlarm() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)

        val repeat = repeatCheckbox?.isChecked

        if (repeat == true) {
            alarmManager?.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
            Toast.makeText(requireContext(), "Ulangi Alarm", Toast.LENGTH_SHORT).show()
        } else {
            alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
        button.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}
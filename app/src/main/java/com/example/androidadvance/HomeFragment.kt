package com.example.androidadvance

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var scrollView: ScrollView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ScrollView
        scrollView = view.findViewById(R.id.lmagang)

        // Inisialisasi ImageView
        val tugas1: ImageView = view.findViewById(R.id.tugas1)
        tugas1.setOnClickListener(this)

        val tugas2: ImageView = view.findViewById(R.id.tugas2)
        tugas2.setOnClickListener(this)

        val tugas3: ImageView = view.findViewById(R.id.tugas3)
        tugas3.setOnClickListener(this)

        val tugas4: ImageView = view.findViewById(R.id.tugas4)
        tugas4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tugas1 -> {
                val viewModelIntent = Intent(requireContext(), ViewModel::class.java)
                startActivity(viewModelIntent)
            }
            R.id.tugas2 -> {
                val sharedPreferenceFragment = SharedPreferenceFragment()
                replaceFragment(sharedPreferenceFragment)
            }
            R.id.tugas3 -> {
                val WorkManagerFragment = WorkManagerFragment()
                replaceFragment(WorkManagerFragment)
            }
            R.id.tugas4 -> {
                val alarmManagerFragment = AlarmManagerFragment()
                replaceFragment(alarmManagerFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

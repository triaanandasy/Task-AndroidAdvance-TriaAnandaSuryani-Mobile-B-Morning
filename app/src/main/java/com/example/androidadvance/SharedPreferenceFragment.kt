package com.example.androidadvance

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class SharedPreferenceFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shared_preference, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editTextName = view.findViewById(R.id.edt_1)
        editTextAge = view.findViewById(R.id.edt_2)
        button = view.findViewById(R.id.saveButton)
        textView = view.findViewById(R.id.textView)
        imageView = view.findViewById(R.id.back)

        button.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("key_name", name)
            editor.putString("key_age", age)
            editor.apply()

            textView.text = "Saved Value: $name, $age"


            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, AnimationFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        val savedName = sharedPreferences.getString("key_name", "")
        val savedAge = sharedPreferences.getString("key_age", "")

        if (savedName != null && savedAge != null && savedName.isNotEmpty() && savedAge.isNotEmpty()) {
            editTextName.setText(savedName)
            editTextAge.setText(savedAge)
            textView.text = "Saved Value: $savedName, $savedAge"
        }

        imageView.setOnClickListener {
            // Pindah ke HomeFragment tanpa animasi
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        return view
    }
}

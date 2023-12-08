package com.example.androidadvance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

open class ViewModel : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val viewModel = ViewModelProvider(this).get(ViewModel2::class.java)

        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.back)

        textView.text = viewModel.angka.toString()

        button.setOnClickListener {
            viewModel.tambahAngka()
            textView.text = viewModel.angka.toString()
        }

        imageView.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}

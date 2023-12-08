package com.example.androidadvance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

open class ViewModelFragment : Fragment() {

    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    private lateinit var viewModel: ViewModel2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_model, container, false)

        viewModel = ViewModelProvider(this).get(ViewModel2::class.java)

        button = view.findViewById(R.id.button)
        textView = view.findViewById(R.id.textView)
        imageView = view.findViewById(R.id.back)

        textView.text = viewModel.angka.toString()

        button.setOnClickListener {
            viewModel.tambahAngka()
            textView.text = viewModel.angka.toString()
        }

        imageView.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        return view
    }
}

package com.example.androidadvance

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var imagelist: Array<Int>
    private lateinit var titlelist: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagelist = arrayOf(
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,


        )

        titlelist = arrayOf(
            "Tahun Anak-anak Muda dari \nIndonesia Ini Berhasil Raih \nBeasiswa WWDC Apple",
            "Jalin Kerjasama dengan Infinite \nLearning, RMIT Hadir di Pusat \nTeknologi NDP Batam",
            "Cybersecurity: Mengunci Dunia \nMaya untuk Keamanan di Era Digital",
            "Tahun Anak-anak Muda dari \nIndonesia Ini Berhasil Raih \nBeasiswa WWDC Apple",
            "Jalin Kerjasama dengan Infinite \nLearning, RMIT Hadir di Pusat \nTeknologi NDP Batam",
            "Cybersecurity: Mengunci Dunia \nMaya untuk Keamanan di Era Digital",
            "Tahun Anak-anak Muda dari \nIndonesia Ini Berhasil Raih \nBeasiswa WWDC Apple",
            "Jalin Kerjasama dengan Infinite \nLearning, RMIT Hadir di Pusat \nTeknologi NDP Batam",
            "Cybersecurity: Mengunci Dunia \nMaya untuk Keamanan di Era Digital",
        )

        dataList = ArrayList()
        getData()

        val adapter = AdapterClass(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.group -> {
                Toast.makeText(requireContext(), "Create new group", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.broadcast -> {
                Toast.makeText(requireContext(), "Create a new broadcast", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.linked -> {
                Toast.makeText(requireContext(), "Check linked devices", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.setting -> {
                Toast.makeText(requireContext(), "Go to settings", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getData() {
        for (i in imagelist.indices) {
            val dataClass = DataClass(imagelist[i], titlelist[i])
            dataList.add(dataClass)
        }
    }
}

package com.example.arknightsguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvOperators: RecyclerView
    private val list = ArrayList<Operator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvOperators = findViewById(R.id.rv_operators)
        rvOperators.setHasFixedSize(true)

        list.addAll(getListOperators())
        showRecyclerList()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.action_list -> {
//                rvOperators.layoutManager = LinearLayoutManager(this)
//            }
//            R.id.action_grid -> {
//                rvOperators.layoutManager = GridLayoutManager(this, 2)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun getListOperators(): ArrayList<Operator> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listOperator = ArrayList<Operator>()
        for (i in dataName.indices) {
            val operator = Operator(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listOperator.add(operator)
        }
        return listOperator
    }

    private fun showRecyclerList() {
        rvOperators.layoutManager = LinearLayoutManager(this)
        val listOperatorAdapter = ListOperatorAdapter(list)
        rvOperators.adapter = listOperatorAdapter
        listOperatorAdapter.setOnItemClickCallback(object : ListOperatorAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Operator) {
                showSelectedOperator(data)
            }
        })
    }

    private fun showSelectedOperator(operator: Operator) {
        Toast.makeText(this, "Kamu memilih " + operator.name, Toast.LENGTH_SHORT).show()
    }
}
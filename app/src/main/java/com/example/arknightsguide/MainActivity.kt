package com.example.arknightsguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        val dataPhotoFaction = resources.obtainTypedArray(R.array.data_photo_faction)
        val dataFullPhoto = resources.obtainTypedArray(R.array.data_full_photo)
        val dataPhotoClass = resources.obtainTypedArray(R.array.data_photo_class)
        val dataArchetype = resources.getStringArray(R.array.data_archetype)
        val dataTraits = resources.getStringArray(R.array.data_traits)
        val dataOperatorOverview = resources.getStringArray(R.array.data_operator_overview)
        val dataReference = resources.getStringArray(R.array.data_reference)
        val listOperator = ArrayList<Operator>()
        for (i in dataName.indices) {
            val operator = Operator(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataPhotoFaction.getResourceId(i, -1), dataFullPhoto.getResourceId(i, -1), dataPhotoClass.getResourceId(i, -1)
            ,dataArchetype[i], dataTraits[i], dataOperatorOverview[i], dataReference[i])
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
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ARKNIGHTS, operator)
        startActivity(intent)
    }

    private fun showDialog(){
        val view = View.inflate(this@MainActivity, R.layout.layout_dialog, null)

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val btn = view.findViewById<Button>(R.id.btnOk)
        btn.setOnClickListener {
            dialog.dismiss()
        }
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        val dialogWindowWidth = (displayWidth * 0.96f).toInt()
        layoutParams.width = dialogWindowWidth
        dialog.window!!.attributes = layoutParams
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuProfile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            R.id.menuInfo -> {
                showDialog()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
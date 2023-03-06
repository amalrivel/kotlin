package com.example.arknightsguide

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARKNIGHTS = "extra_arknights"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Detail Operator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rvFullOperator:ImageView = findViewById(R.id.img_item_full_photo)
        val rvOperatorFaction:ImageView = findViewById(R.id.img_item_photo_faction)
        val rvOperatorClass:ImageView = findViewById(R.id.img_item_photo_class)
        val rvName:TextView = findViewById(R.id.name)
        val rvArchetype:TextView = findViewById(R.id.operator_archetype)
        val rvTrait:TextView = findViewById(R.id.operator_traits)
        val rvDescription:TextView = findViewById(R.id.operator_description)
        val rvOverview:TextView = findViewById(R.id.operator_overview)
        val rvReference:TextView = findViewById(R.id.operator_reference)
        val btnShare:FloatingActionButton = findViewById(R.id.action_share)

        val operator = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ARKNIGHTS, Operator::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ARKNIGHTS)
        }

        btnShare.setOnClickListener { shareContent(operator) }

        rvReference.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rvReference.text.toString()))
            startActivity(intent)
        }

        if (operator != null) {
            rvFullOperator.setImageResource(operator.fullPhoto)
            rvOperatorFaction.setImageResource(operator.faction)
            rvOperatorClass.setImageResource(operator.operatorClass)
            rvName.text = operator.name
            rvArchetype.text = operator.archetype
            rvTrait.text = operator.traits
            rvOverview.text = operator.operatorOverview
            rvDescription.text = operator.description

            rvReference.text = operator.reference
        }
    }

    private fun shareContent(operator: Operator?) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ARKNIGHTS GUIDE")
        if (operator != null) {
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Arknights Guide ${operator.name}.\nRead more in ${operator.reference}")
        }
        startActivity(Intent.createChooser(shareIntent, "Share Using"))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

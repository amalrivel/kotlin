package com.example.arknightsguide

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARKNIGHTS = "extra_arknights"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val rvFullOperator:ImageView = findViewById(R.id.img_item_full_photo)
        val rvOperatorFaction:ImageView = findViewById(R.id.img_item_photo_faction)

        val operator = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ARKNIGHTS, Operator::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ARKNIGHTS)
        }

        if (operator != null) {
            rvFullOperator.setImageResource(operator.fullPhoto)
            rvOperatorFaction.setImageResource(operator.faction)
        }
    }
}
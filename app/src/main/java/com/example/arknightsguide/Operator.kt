package com.example.arknightsguide

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Operator(
    val name: String,
    val description: String,
    val photo: Int,
    val faction: Int,
    val fullPhoto: Int,
    val operatorClass: Int,
    val archetype:String,
    val traits:String,
    val operatorOverview:String,
    val reference:String
) : Parcelable
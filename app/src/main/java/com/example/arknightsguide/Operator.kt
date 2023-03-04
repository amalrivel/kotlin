package com.example.arknightsguide

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Operator(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
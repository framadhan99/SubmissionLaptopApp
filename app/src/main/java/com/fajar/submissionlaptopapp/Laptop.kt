package com.fajar.submissionlaptopapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Laptop(
    val name: String,
    val price: String,
    val desc: String,
    val image: Int,
    val link: String
) : Parcelable

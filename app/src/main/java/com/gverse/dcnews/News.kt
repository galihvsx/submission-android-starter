package com.gverse.dcnews

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val description: String,
    val date: String,
    val thumbnail: Int
) : Parcelable

package com.example.coursesgridapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicNameStringResourceId: Int,
    val numberOfCourses: Int,
    @DrawableRes val imageResourceId: Int,
)

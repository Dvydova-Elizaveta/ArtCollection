package com.example.artcollection.presentation.details

import android.telecom.Call
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailsErrorScreen(
    message: String,
    onBack: () -> Unit
) {
    Text(message)
}
package com.example.linguistic.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WordCardScreen(words: List<Pair<String, String>>) {
    LazyColumn(
        Modifier
            .background(Color(0xff44475a))
            .fillMaxSize()
    ) {
        items(words) { wordPair ->
            WordCard(wordPair.first, wordPair.second)
        }
    }
}

@Composable
fun WordCard(word: String, translation: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var isDismissed by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = !isDismissed,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        change.consume()
                        if (dragAmount > 50) {
                            isDismissed = true
                        }
                    }
                },
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { isExpanded = !isExpanded }
            ) {
                Text(text = word, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                if (isExpanded) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = translation, fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWordCardScreen() {
    val words: List<Pair<String, String>> = listOf(
        "Hello" to "Привет",
        "Goodbye" to "До свидания",
        "Please" to "Пожалуйста",
        "Thank you" to "Спасибо"
    )
    WordCardScreen(words)
}
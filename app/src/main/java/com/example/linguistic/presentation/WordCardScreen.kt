package com.example.linguistic.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WordCardScreen(words: List<Pair<String, String>>) {
    LazyColumn {
        items(words) { wordPair ->
            WordCard(wordPair.first, wordPair.second)
        }
    }
}

@Composable
fun WordCard(word: String, translation: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = word, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = translation, fontSize = 20.sp)
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
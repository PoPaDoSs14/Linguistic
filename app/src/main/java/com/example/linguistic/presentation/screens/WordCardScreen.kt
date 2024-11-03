package com.example.linguistic.presentation.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.linguistic.domain.Level
import com.example.linguistic.presentation.WordCardScreenViewModel

@Composable
fun WordCardScreen(
    words: List<Pair<String, String>>,
    viewModel: WordCardScreenViewModel,
    navController: NavHostController,
    level: Level
) {


    val difficultyLevel =
        if (level == Level.EASY) "Сложность: легко"
        else if (level == Level.MEDIUM) "Сложность: нормально" else "Сложность: тяжело"

    val color =
        if (level == Level.EASY) Color.Green
        else if (level == Level.MEDIUM) Color.Yellow
        else Color.Red



    Column(
        modifier = Modifier
            .background(Color(0xff44475a))
            .fillMaxSize()
    ) {


        Text(
            text = difficultyLevel,
            color = color,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )


        LazyColumn {
            items(words) { wordPair ->
                WordCard(wordPair.first, wordPair.second, viewModel)
            }
        }


        Button(
            onClick = { navController.navigate("UserStatsScreen") },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Посмотреть статистику")
        }
    }
}

@Composable
fun WordCard(word: String, translation: String, viewModel: WordCardScreenViewModel) {
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
                .clickable { isExpanded = !isExpanded }
                .background(MaterialTheme.colorScheme.primary)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        change.consume()
                        if (dragAmount > 50) {
                            isDismissed = true
                            viewModel.addKnowWord()
                        }
                    }
                },
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
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


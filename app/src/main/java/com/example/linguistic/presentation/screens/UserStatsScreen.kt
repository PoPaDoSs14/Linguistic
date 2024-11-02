package com.example.linguistic.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.linguistic.presentation.UserStatsViewModel


@Composable
fun UserStatsScreen(viewModel: UserStatsViewModel) {
    val userName by viewModel.userName
    val knownWordsCount by viewModel.knownWordsCount
    val userRating by viewModel.userRating
    val avatarUri by viewModel.avatarUri

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff44475a))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        avatarUri?.let { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(8.dp)
                    .shadow(4.dp, CircleShape)
            )
        } ?: run {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Стандартный аватар",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = userName,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "Количество знакомых слов: $knownWordsCount",
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "Рейтинг: $userRating",
            color = Color.White
        )
    }
}
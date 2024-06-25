package com.example.nytimesapp.ui.screen.nyarticledetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nytimesapp.ui.model.NyTimesArticle
import com.example.nytimesapp.ui.screen.nyarticlelist.NyTimesViewModel

@Composable
fun NyTimesArticleDetailScreen(
    viewModel: NyTimesViewModel = hiltViewModel()
) {
    val nyTimesState = viewModel.nyTimesState.collectAsState()
    NewsArticleScreen(nyTimesState.value.selectedArticle)
}

@Composable
private fun NewsArticleScreen(
    nyTimesArticle: NyTimesArticle
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Image from URL
        AsyncImage(
            model = nyTimesArticle.imageUrl,
            contentDescription = null,
            placeholder = painterResource(com.example.nytimesapp.R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // Title
        Text(
            text = nyTimesArticle.title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = nyTimesArticle.abstract,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Published date and source
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Published: ${nyTimesArticle.publishedDate}\nSource: ${nyTimesArticle.source}\nBy: ${nyTimesArticle.byLine}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black
            )
        }
    }
}
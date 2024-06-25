package com.example.nytimesapp.ui.screen.nyarticlelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nytimesapp.R
import com.example.nytimesapp.ui.model.NyTimesArticle
import kotlinx.coroutines.launch

@Composable
fun NyTimesScreen(
    viewModel: NyTimesViewModel = hiltViewModel(),
    onItemClick: (NyTimesArticle) -> Unit
) {
    val nyTimesState by viewModel.nyTimesState.collectAsState()
    val scaffoldState = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    if (nyTimesState.error.isNotEmpty()) {
        LaunchedEffect(nyTimesState.error) {
            scaffoldState.launch {
                val result = snackBarHostState.showSnackbar(
                    message = nyTimesState.error,
                    actionLabel = "Try Again",
                    duration = SnackbarDuration.Indefinite
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.resetError()
                        viewModel.getMostViewedArticles()
                    }

                    SnackbarResult.Dismissed -> {
                        viewModel.resetError()
                        // Optionally handle dismiss
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        if (viewModel.nyTimesState.value.isDataFetched.not())
            viewModel.getMostViewedArticles()
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
    ) {
        if (nyTimesState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (nyTimesState.NYTimes.results.isNotEmpty())
                LazyColumn {
                    items(nyTimesState.NYTimes.results) { article ->
                        ArticleItem(
                            article = article,
                            onItemClick = { nyTimes ->
                                viewModel.selectArticle(nyTimes)
                                onItemClick.invoke(nyTimes)
                            }
                        )
                    }
                }
        }
    }
}

@Composable
fun ArticleItem(
    article: NyTimesArticle,
    onItemClick: (NyTimesArticle) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onItemClick(article) }) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 8.dp)
                .clip(CircleShape)
                .size(
                    width = 48.dp,
                    height = 48.dp
                )
                .background(color = Color.LightGray),
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.abstract,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}


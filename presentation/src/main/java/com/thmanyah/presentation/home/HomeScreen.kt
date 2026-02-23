package com.thmanyah.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thmanyah.domain.model.SectionType
import com.thmanyah.presentation.composable.BigSquareSection
import com.thmanyah.presentation.composable.GridSection
import com.thmanyah.presentation.composable.LoadingIndicator
import com.thmanyah.presentation.composable.LoadingMoreIndicator
import com.thmanyah.presentation.composable.QueueSection
import com.thmanyah.presentation.composable.SectionTitle
import com.thmanyah.presentation.composable.SquareSection
import com.thmanyah.presentation.extension.OnBottomReached
import com.thmanyah.presentation.home.contract.HomeContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToSearchScreen: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message ?: "Something went wrong. Please try again later")
                }

                HomeContract.Effect.NavigateToSearchScreen -> {
                    navigateToSearchScreen.invoke()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(8.dp),
                title = {
                    Text(text = "Home Screen")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.setEvent(HomeContract.Event.SearchClicked)
                        },
                        modifier = Modifier.testTag("search_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ) { padding ->
        if (state.loading)
            LoadingIndicator(modifier = Modifier.padding(padding))
        else
            state.sections?.data?.let { sections ->
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(sections, key = { it.id }) { section ->
                        SectionTitle(title = section.title)
                        when (section.type) {
                            SectionType.QUEUE -> QueueSection(items = section.items)
                            SectionType.SQUARE -> SquareSection(items = section.items)
                            SectionType.TWO_LINES_GRID -> GridSection(items = section.items)
                            SectionType.BIG_SQUARE -> BigSquareSection(items = section.items)
                        }
                    }
                    if (state.loadingMore)
                        item(key = "load_more") {
                            LoadingMoreIndicator()
                        }
                }
                listState.OnBottomReached {
                    viewModel.setEvent(HomeContract.Event.LoadMoreData)
                }
            }
    }

}
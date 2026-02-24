package com.thmanyah.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thmanyah.domain.model.SectionType
import com.thmanyah.presentation.composable.BigSquareSection
import com.thmanyah.presentation.composable.GridSection
import com.thmanyah.presentation.composable.LoadingIndicator
import com.thmanyah.presentation.composable.QueueSection
import com.thmanyah.presentation.composable.SectionTitle
import com.thmanyah.presentation.composable.SquareSection
import com.thmanyah.presentation.search.contract.SearchContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        effect.message ?: "Something went wrong. Please try again later"
                    )
                }

                SearchContract.Effect.NavigateBack -> {
                    onNavigateBack.invoke()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Search Screen")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.setEvent(SearchContract.Event.BackClicked) },
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            OutlinedTextField(
                modifier = Modifier
                    .testTag("query_field")
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                value = state.query,
                placeholder = { Text(text = "Type 3 Characters at least") },
                onValueChange = { query ->
                    viewModel.setEvent(SearchContract.Event.SearchData(query))
                }
            )
            if (state.loading)
                LoadingIndicator()
            else
                state.sections?.let { sections ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight(1f)
                    ) {
                        items(
                            sections,
                            key = { section -> section.id },
                            contentType = { section -> section.contentType }
                        ) { section ->
                            SectionTitle(title = section.title)
                            when (section.type) {
                                SectionType.QUEUE -> QueueSection(items = section.items)
                                SectionType.SQUARE -> SquareSection(items = section.items)
                                SectionType.TWO_LINES_GRID -> GridSection(items = section.items)
                                SectionType.BIG_SQUARE -> BigSquareSection(items = section.items)
                            }
                        }
                    }
                }
        }
    }

}
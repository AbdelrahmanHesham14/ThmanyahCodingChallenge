package com.thmanyah.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thmanyah.domain.model.ContentItem

@Composable
fun BigSquareSection(
    modifier: Modifier = Modifier,
    items: List<ContentItem>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(items, key = { it.id }) {
            BigSquareItem(item = it)
        }
    }
}

@Composable
fun BigSquareItem(
    modifier: Modifier = Modifier,
    item: ContentItem
) {
    Box(
        modifier = modifier
            .size(220.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        NetworkImage(
            url = item.imageUrl,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = item.name,
            fontSize = 12.sp,
            maxLines = 1,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 4.dp, vertical = 4.dp)
        )
    }
}
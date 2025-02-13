package com.example.pagerapplication.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.example.pagerapplication.R
import kotlinx.coroutines.launch


data class TabItem(val title: String?, val icon: ImageVector?)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun horizontalPagerWithTabsAndIcon() {
    val tabs = listOf(
        TabItem("Home", null),
        TabItem("Pay", null),
        TabItem("Cards", null),
        TabItem("Wealth", null),
        TabItem(null, Icons.Default.Menu)
    )
    val pagerState = rememberPagerState(initialPage = 0,0f) { 5 }
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            tabs.forEachIndexed { index, tab ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (tab.icon != null) {
                        Icon(
                            modifier = Modifier
                                .size(34.dp)
                                .padding(top = 12.dp),
                            painter = painterResource(id = R.drawable.baseline_menu_24),
                            contentDescription = "Menu"
                        )
                    } else if (tab.title == "Home") {
                        homeIcon()
                    } else {
                        Text(
                            text = tab.title ?: "",
                            modifier = Modifier
                                .padding(8.dp)
                                .focusable()
                        )
                    }
                    if (pagerState.currentPage == index) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(Color.Red)
                        )
                    }
                }
            }
        }
        val coroutineScope = rememberCoroutineScope()

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (tabs[page].title == "Home") {
                    homeContent()
                } else if (tabs[page].title != null) {
                    Text(
                        text = "This is mock ${tabs[page].title} hub plugin",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                } else {
                    Text(
                        text = "This is mock ${tabs[page].icon} hub plugin",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}


@Composable
fun homeIcon(
    modifier: Modifier = Modifier
) {
    val iconFocusRequester = remember { FocusRequester() }
    Icon(
        modifier = modifier
            .size(34.dp)
            .padding(
                top = 9.dp,
                start = 9.dp,
                end = 0.dp
            )
            .focusable()
            .focusRequester(iconFocusRequester),
        painter = painterResource(id = R.drawable.baseline_home_24),
        contentDescription = "Home"
    )
}

@Composable
fun homeContent(){
    Box(
        modifier = Modifier
    ){
        Text(
            text = "This is mock Home hub plugin",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
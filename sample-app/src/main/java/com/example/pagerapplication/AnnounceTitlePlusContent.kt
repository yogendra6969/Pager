package com.example.pagerapplication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material3.Text
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription

data class TabItem(val title: String?, val icon: ImageVector?)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun horizontalPagerWithTabsAndIcon99() {
    val tabs = listOf(
        TabItem(null, null), // Placeholder for HomeIcon
        TabItem("Pay", null),
        TabItem("Cards", null),
        TabItem("Wealth", null),
        TabItem(null, Icons.Default.Menu) // Menu tab
    )
    val pagerState = rememberPagerState(initialPage = 0) { tabs.size }
    val coroutineScope = rememberCoroutineScope()
    val focusRequesters = remember { List(tabs.size) { FocusRequester() } }

    Column {
        // Tabs Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            tabs.forEachIndexed { index, tab ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequesters[index])
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (index == 0) {
                        homeIcon(
                            modifier = Modifier
                                .size(34.dp)
                                .padding(top = 9.dp)
                        )
                    } else if (tab.icon != null) {
                        Icon(
                            modifier = Modifier
                                .size(34.dp)
                                .padding(top = 12.dp)
                                .focusable(),
                            imageVector = tab.icon,
                            contentDescription = "Menu"
                        )
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

        // Horizontal Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .semantics {
                        // Announce the tab title first, then the content
                        contentDescription = if (tabs[page].title != null) {
                            "Tab: ${tabs[page].title}, Content: ${R.string.dummy_text}"
                        } else {
                            "Tab: Menu, Content: ${R.string.dummy_text}"
                        }
                    }
            ) {
                when (page) {
                    0 -> homeContent()
                    else -> {
                        Column {
                            Text(
                                text = if (tabs[page].title != null) {
                                    "This is mock ${tabs[page].title} hub plugin"
                                } else {
                                    "This is mock Menu hub plugin"
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            HubdummyConent()
                        }
                    }
                }
            }
        }

        // Programmatically shift focus when the page changes

    }
}

@Composable
fun homeIcon(
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier
            .size(54.dp)
            .padding(
                top = 3.dp,
                start = 3.dp,
                end = 0.dp
            )
            .focusable(),
        painter = painterResource(id = R.drawable.baseline_home_24),
        contentDescription = "Home"
    )
}

@Composable
fun homeContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "This is mock Home hub plugin",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
        HubdummyConent()
    }
}

@Composable
fun HubdummyConent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .semantics {
                // Set additional semantics for screen reader to read the content
                contentDescription = R.string.dummy_text.toString()
            }
    ) {
        repeat(2) {
            Text(
                text = stringResource(R.string.dummy_text),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
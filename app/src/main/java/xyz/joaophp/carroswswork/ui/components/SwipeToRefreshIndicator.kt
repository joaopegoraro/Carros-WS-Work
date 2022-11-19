package xyz.joaophp.carroswswork.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun SwipeToRefreshIndicator(
    state: SwipeRefreshState,
    trigger: Dp,
) {
    SwipeRefreshIndicator(
        modifier = Modifier.testTag("SWIPE_REFRESH"),
        state = state,
        refreshTriggerDistance = trigger,
        scale = true,
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.primary
    )
}

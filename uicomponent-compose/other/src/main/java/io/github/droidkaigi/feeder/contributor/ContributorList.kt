package io.github.droidkaigi.feeder.contributor

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.toPaddingValues
import io.github.droidkaigi.feeder.Contributor
import io.github.droidkaigi.feeder.core.theme.ConferenceAppFeederTheme
import io.github.droidkaigi.feeder.core.use

@Composable
fun ContributorList(onContributorClick: (Contributor) -> Unit) {

    val (
        state,
        effectFlow,
        dispatch,
    ) = use(contributeViewModel())

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 3),
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars,
                applyStart = false,
                applyTop = false,
                applyEnd = false
            ),
            content = {
                items(state.contributorContents.size) { contributor ->
                    ContributorItem(contributor = state.contributorContents.get(contributor), onContributorClick)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContributorScreen() {
    ConferenceAppFeederTheme {
        CompositionLocalProvider(
            provideContributorViewModelFactory {
                fakeContributorViewModel()
            }
        ) {
            ContributorList() {
            }
        }
    }
}

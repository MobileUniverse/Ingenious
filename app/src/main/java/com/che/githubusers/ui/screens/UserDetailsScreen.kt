package com.che.githubusers.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.che.githubusers.R
import com.che.githubusers.domain.fakes.FAKE_USERS
import com.che.githubusers.domain.models.GithubUser
import com.che.githubusers.ui.theme.GithubusersTheme

@Composable
internal fun UserDetailsScreen(user: GithubUser?) {
    user?.run {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text = id.toString())
            Text(text = login)
            Text(text = url)
        }
    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.noInformation))
    }
}

@PreviewLightDark
@Composable
private fun UserDetailsScreenPreview() {
    GithubusersTheme {
        UserDetailsScreen(user = FAKE_USERS.first())
    }
}

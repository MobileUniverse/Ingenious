package com.che.githubusers.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.che.githubusers.domain.fakes.FAKE_USERS
import com.che.githubusers.domain.models.GithubUser
import com.che.githubusers.ui.theme.GithubusersTheme
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun UsersScreen(
    users: PersistentList<GithubUser>,
    onClick: (GithubUser) -> Unit
) {
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        items(users) { user ->
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onClick(user) },
                        modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        user.login
                    )
                }
            }
            HorizontalDivider(thickness = 4.dp)
        }
    }
}

@PreviewLightDark
@Composable
private fun UsersScreenPreview() {
    GithubusersTheme {
        UsersScreen(users = FAKE_USERS, {})
    }
}

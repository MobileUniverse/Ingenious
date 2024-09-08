package com.che.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.che.githubusers.navigation.UserScreensGraph.UserScreenDetails
import com.che.githubusers.navigation.UserScreensGraph.UsersScreen
import com.che.githubusers.ui.screens.FailureDialog
import com.che.githubusers.ui.screens.LoadingIndicator
import com.che.githubusers.ui.screens.UserDetailsScreen
import com.che.githubusers.ui.screens.UsersScreen
import com.che.githubusers.ui.theme.GithubusersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.fetchGithubUsers()

        setContent {
            GithubusersTheme {
                Scaffold { innerPadding ->
                    NavigationHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun NavigationHost(
        modifier: Modifier = Modifier
    ) {
        val navController = rememberNavController()
        val usersState = viewModel.usersState.collectAsState()
        val gGithubUserState = viewModel.gGithubUserState.collectAsState()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = UsersScreen.destination
        ) {
            composable(UsersScreen.destination) {
                usersState.value.let {
                    when {
                        it.isLoading -> LoadingIndicator()
                        it.errorMessage -> FailureDialog(
                            title = stringResource(id = R.string.error),
                            text = "",
                            onDismiss = { viewModel.onUsersStateErrorClose() }
                        )

                        else -> UsersScreen(
                            users = it.users
                        ) { user ->
                            viewModel.getUserDetails(user.login)
                            navController.navigate(UserScreenDetails.destination)
                        }
                    }
                }
            }

            composable(
                route = UserScreenDetails.destination
            ) {
                gGithubUserState.value.let {
                    when {
                        it.isLoading -> LoadingIndicator()
                        it.errorMessage -> FailureDialog(
                            title = stringResource(id = R.string.error),
                            text = "",
                            onDismiss = { viewModel.onGithubUserStateErrorClose() }
                        )

                        else -> UserDetailsScreen(user = it.githubUser)
                    }
                }
            }
        }
    }
}

package com.che.githubusers.navigation

internal sealed interface UserScreensGraph {
    val destination: String

    data object UsersScreen : UserScreensGraph {
        override val destination: String = "users_screen"
    }

    data object UserScreenDetails : UserScreensGraph {
        override val destination: String = "user_screen_details"
    }
}

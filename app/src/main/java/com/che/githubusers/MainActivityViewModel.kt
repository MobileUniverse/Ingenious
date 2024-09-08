package com.che.githubusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.che.githubusers.domain.models.GithubUsersCriteria
import com.che.githubusers.domain.usecase.GithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor(
    private val githubUserUseCase: GithubUserUseCase
) : ViewModel() {

    private val _usersState = MutableStateFlow(MainScreenStates.UsersState())
    val usersState = _usersState.asStateFlow()

    private val _gGithubUserState = MutableStateFlow(MainScreenStates.GithubUserState())
    val gGithubUserState = _gGithubUserState.asStateFlow()

    fun fetchGithubUsers(lastId: Long = 0) {
        viewModelScope.launch {
            _usersState.update { it.copy(isLoading = true, errorMessage = false) }
            githubUserUseCase.getGithubUsers(GithubUsersCriteria(initialId = lastId))
                .onEach { users ->
                    _usersState.update {
                        if (users.isNotEmpty()) {
                            it.copy(users = users, isLoading = false)
                        } else {
                            it.copy(errorMessage = true, isLoading = false)
                        }
                    }
                }.launchIn(this)
        }
    }

    fun getUserDetails(login: String) {
        viewModelScope.launch {
            _gGithubUserState.update { it.copy(isLoading = true, errorMessage = false) }
            githubUserUseCase.getGithubUser(login)
                .onEach { user ->
                    _gGithubUserState.update { currentState ->
                        user?.let {
                            currentState.copy(githubUser = it, isLoading = false)
                        } ?: currentState.copy(
                            isLoading = false,
                            errorMessage = true
                        )
                    }
                }.launchIn(this)
        }
    }

    fun onUsersStateErrorClose() {
        _usersState.update { it.copy(errorMessage = false) }
    }

    fun onGithubUserStateErrorClose() {
        _gGithubUserState.update { it.copy(errorMessage = false) }
    }
}

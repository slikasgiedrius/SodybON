package com.giedrius.slikas.sodybon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giedrius.slikas.sodybon.data.article.ArticleRepository
import com.giedrius.slikas.sodybon.data.article.model.Article
import com.giedrius.slikas.sodybon.data.user.UserRepository
import com.giedrius.slikas.sodybon.data.user.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class HomeScreenUiState(
    var users: List<User> = emptyList(),
    var articles: List<Article> = emptyList()
)

class HomeViewModel(
    private val articleRepository: ArticleRepository,
    private val usersRepository: UserRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            //Launch only one otherwise it crashes ios
            updateUsers()
//            updateArticles()
        }
    }

    private fun updateUsers() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(users = usersRepository.getUsers())
            }
        }
    }

    private fun updateArticles() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(articles = articleRepository.getArticles().articles)
            }
        }
    }
}
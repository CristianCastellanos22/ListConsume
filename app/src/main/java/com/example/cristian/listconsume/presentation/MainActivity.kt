package com.example.cristian.listconsume.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cristian.listconsume.R
import com.example.cristian.listconsume.presentation.viewmodel.CategoriesViewModel
import com.example.cristian.listconsume.presentation.widgets.ErrorWidget
import com.example.cristian.listconsume.presentation.widgets.LoadingWheel
import com.example.cristian.listconsume.presentation.widgets.ShowElements
import com.example.cristian.listconsume.ui.theme.ListConsumeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val categoriesViewModel: CategoriesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListConsumeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ObserveGetCategories(categoriesViewModel)
                    categoriesViewModel.getCategories()
                }
            }
        }

    }
}

@Composable
private fun ObserveGetCategories(categoriesViewModel: CategoriesViewModel) {
    val status by categoriesViewModel.status.observeAsState()
    when (status) {
        is ViewState.Loading -> {
            LoadingWheel()
        }

        is ViewState.Success -> {
            val categories = (status as ViewState.Success).data
            ShowCategories(elements = categories)
        }

        is ViewState.Error -> {
            ErrorWidget()
        }

        else -> {
            Log.d("MainActivity", "No hay categorías")
        }
    }
}

@Composable
private fun ShowCategories(elements: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.title),
            textAlign = TextAlign.Center,
        )
        ShowElements(elements = elements)
    }
}

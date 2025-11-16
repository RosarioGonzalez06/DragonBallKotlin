package com.turingalan.pokemon.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage


@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterDetailScreen(
        modifier = modifier,
        name = uiState.name,
        image =  uiState.image,
    )


}

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    name: String,
    image: String?,
    )
{

    Column(modifier = modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally) {
        if (image != null)  {
            AsyncImage(
                model = image,
                contentScale = ContentScale.Crop,
                contentDescription = name
            )
        }
    }

}

//@Preview
//@Composable
//fun PokemonDetailScreenPreview() {
//    Surface {
//        PokemonDetailScreen(name = "Eeve", artwork = R.drawable.artwork_133)
//
//    }
//
//}
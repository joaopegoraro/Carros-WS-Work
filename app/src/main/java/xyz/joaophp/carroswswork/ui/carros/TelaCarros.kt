package xyz.joaophp.carroswswork.ui.carros

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel
import xyz.joaophp.carroswswork.data.Carro

@Composable
fun TelaCarros(viewModel: CarrosViewModel = getViewModel()) {
    LazyColumn() {
    }
}

@Composable
private fun CarroListItem(
    carro: List<Carro>,
    salvarCarro: (Carro) -> Unit,
    excluirCarro: (Carro) -> Unit
) {

}
package xyz.joaophp.carroswswork.ui.carros

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.R
import xyz.joaophp.carroswswork.utils.ApiResult

@Composable
fun TelaCarros(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: CarrosViewModel = getViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    val snackbarScope = rememberCoroutineScope()
    LaunchedEffect(true) {
        viewModel.atualizarLista(
            onFailure = { erro ->
                snackbarScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(context.getMensagemErro(erro))
                }
            }
        )
    }

    val carros by state.carros.collectAsState(initial = emptyList())
    LazyColumn(modifier = modifier) {
        items(carros) { carro ->
            CarroListItem(
                carro = carro,
                salvarCarro = {
                    // TODO
                },
                excluirCarro = {
                    // TODo
                }
            )
        }
    }
}

@Composable
private fun CarroListItem(
    carro: Carro,
    salvarCarro: (Carro) -> Unit,
    excluirCarro: (Carro) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(carro.nomeModelo.ifEmpty { "SEM NOME" })
    }
}

private fun Context.getMensagemErro(erro: ApiResult.Error): String {
    return when (erro) {
        is ApiResult.Error.ErroServidor -> {
            getString(R.string.erro_servidor_buscar_carros)
        }
        is ApiResult.Error.FalhaConexao -> {
            getString(R.string.falha_conexao_buscar_carros)
        }
        is ApiResult.Error.ErroDesconhecido -> {
            getString(R.string.erro_desconhecido_buscar_carros)
        }
    }
}
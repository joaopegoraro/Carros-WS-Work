package xyz.joaophp.carroswswork.ui.carros

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import xyz.joaophp.carroswswork.R
import xyz.joaophp.carroswswork.ui.CarroViewModel
import xyz.joaophp.carroswswork.ui.components.DialogCadastrarEmail
import xyz.joaophp.carroswswork.ui.components.ListaCarros
import xyz.joaophp.carroswswork.ui.components.NoResultsFoundLayout
import xyz.joaophp.carroswswork.ui.theme.wsPink
import xyz.joaophp.carroswswork.ui.theme.wsWhite
import xyz.joaophp.carroswswork.utils.ApiResult
import java.util.*

@Composable
fun TelaCarros(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: CarroViewModel = getViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state
    val perfil by state.perfil.collectAsState(initial = null)
    val carros by state.carros.collectAsState(initial = emptyList())
    val leads by state.leads.collectAsState(initial = emptyList())

    val snackbarScope = rememberCoroutineScope()
    LaunchedEffect(true) {
        viewModel.atualizarLista(
            setLoading = {
                if (carros.isEmpty()) state.isLoading = it
            },
            onFailure = { erro ->
                snackbarScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(context.getMensagemErro(erro))
                }
            }
        )
    }

    val onRefresh = {
        viewModel.atualizarLista(
            setLoading = { state.isLoadingSwipeToRefresh = it },
            onFailure = { erro ->
                snackbarScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(context.getMensagemErro(erro))
                }
            }
        )
    }

    ListaCarros(
        modifier = modifier,
        carros = carros,
        leads = leads,
        swipeToRefreshEnabled = true,
        isLoadingOnRefresh = state.isLoadingSwipeToRefresh,
        noResultsFound = {
            NoResultsFoundLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                icon = rememberVectorPainter(Icons.Default.Refresh),
                text = stringResource(R.string.lista_carros_vazia),
                onClick = onRefresh
            )
        },
        onRefresh = onRefresh,
        botaoListItem = { carro, carroSalvoBanco ->
            BotaoSalvar(
                scaffoldState =scaffoldState,
                emailUsuario = perfil?.email,
                carroSalvoBanco = carroSalvoBanco,
                salvarCarro = {
                    viewModel.favoritarCarro(carro)
                },
                excluirCarro = {
                    viewModel.removerFavoritoCarro(carro)
                }
            )

        }
    )
}

@Composable
private fun BotaoSalvar(
    scaffoldState: ScaffoldState,
    emailUsuario: String?,
    carroSalvoBanco: Boolean,
    salvarCarro: () -> Unit,
    excluirCarro: () -> Unit
) {
    var carroSalvo by remember(carroSalvoBanco) {
        mutableStateOf(carroSalvoBanco)
    }
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (carroSalvo) 1f else 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    val drawerScope = rememberCoroutineScope()
    var mostrarDialog by remember {
        mutableStateOf(false)
    }
    if (mostrarDialog) {
        DialogCadastrarEmail(
            onDismiss = {
                mostrarDialog = false
            },
            onConfirm = {
                drawerScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        )
    }

    Button(
        modifier = Modifier
            .scale(scale)
            .animateContentSize(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = wsPink
        ),
        onClick = {
            if (carroSalvo) {
                excluirCarro()
                carroSalvo = !carroSalvo
            } else if (emailUsuario.isNullOrEmpty()) {
                mostrarDialog = true
            } else {
                salvarCarro()
                carroSalvo = !carroSalvo
            }
        },
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(
                if (carroSalvo) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            ),
            contentDescription = null,
            tint = wsWhite,
        )
        AnimatedVisibility(!carroSalvo) {
            Spacer(Modifier.size(15.dp))
            Text(
                text = stringResource(R.string.eu_quero).uppercase(Locale.getDefault()),
                fontWeight = FontWeight.SemiBold
            )
        }
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

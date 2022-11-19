package xyz.joaophp.carroswswork.ui.carros

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import xyz.joaophp.carroswswork.R
import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.ui.ViewModel
import xyz.joaophp.carroswswork.ui.theme.wsPink
import xyz.joaophp.carroswswork.ui.theme.wsWhite
import xyz.joaophp.carroswswork.utils.ApiResult
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TelaCarros(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: ViewModel = getViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state
    val carros by state.carros.collectAsState(initial = emptyList())

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

    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(state.isLoadingSwipeToRefresh),
        indicator = { swipeState, trigger -> SwipeRefreshIndicator(swipeState, trigger) },
        onRefresh = {
            viewModel.atualizarLista(
                setLoading = { state.isLoadingSwipeToRefresh = it },
                onFailure = { erro ->
                    snackbarScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(context.getMensagemErro(erro))
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items = carros) { carro ->
                CarroListItem(
                    modifier = Modifier.animateItemPlacement(),
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
}

@Composable
private fun CarroListItem(
    modifier: Modifier = Modifier,
    carro: Carro,
    salvarCarro: (Carro) -> Unit,
    excluirCarro: (Carro) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var carroSalvo by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth()
            .animateContentSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    isExpanded = !isExpanded
                }
            ),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = getLogomarca(carro.marcaNome),
                    contentDescription = stringResource(
                        R.string.cs_logomarca_carro,
                        carro.marcaNome
                    )
                )
                Column(
                    modifier = Modifier.weight(1f, false),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = carro.nomeModelo.ifEmpty {
                                stringResource(R.string.modelo_desconhecida)
                            },
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onBackground
                        )
                        carro.ano?.let { ano ->
                            Text(
                                text = ano.toString(),
                                fontSize = 15.sp
                            )
                        }
                    }
                    Text(
                        text = carro.marcaNome.ifEmpty {
                            stringResource(R.string.marca_desconhecida)
                        },
                        fontSize = 15.sp
                    )
                    Text(
                        text = stringResource(
                            id = R.string.combustivel_label,
                            carro.combustivel.ifEmpty { stringResource(R.string.nao_informado) }
                        )
                    )
                    Text(
                        text = stringResource(R.string.numero_portas, carro.numeroPortas)
                    )
                }
            }
            if (isExpanded) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (carro.valorFipe == null) {
                            stringResource(R.string.nao_informado)
                        } else {
                            stringResource(R.string.preco_label, carro.valorFipe)
                        },
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    val infiniteTransition = rememberInfiniteTransition()
                    val scale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = if (carroSalvo) 1f else 1.1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    Button(
                        modifier = Modifier
                            .scale(scale)
                            .animateContentSize(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = wsPink
                        ),
                        onClick = {
                            carroSalvo = !carroSalvo
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
            }
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

@Composable
private fun getLogomarca(marca: String): Painter {
    return when (marca) {
        Carro.MARCA_FIAT -> {
            painterResource(R.drawable.fiat_logo)
        }
        Carro.MARCA_FORD -> {
            painterResource(R.drawable.ford_logo)
        }
        Carro.MARCA_TOYOTA -> {
            painterResource(R.drawable.toyota_logo)
        }
        Carro.MARCA_VW -> {
            painterResource(R.drawable.volkswagen_logo)
        }
        else -> {
            painterResource(R.drawable.logows)
        }
    }

}

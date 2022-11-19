package xyz.joaophp.carroswswork.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import xyz.joaophp.carroswswork.R
import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.data.Lead
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListaCarros(
    modifier: Modifier = Modifier,
    carros: List<Carro>,
    leads: List<Lead>,
    swipeToRefreshEnabled: Boolean,
    isLoadingOnRefresh: Boolean = false,
    onRefresh: () -> Unit = {},
    noResultsFound: @Composable () -> Unit,
    botaoListItem: @Composable (carro: Carro, carroSalvo: Boolean) -> Unit
) {
    SwipeRefresh(
        modifier = modifier,
        swipeEnabled = swipeToRefreshEnabled,
        state = rememberSwipeRefreshState(isLoadingOnRefresh),
        indicator = { swipeState, trigger -> SwipeRefreshIndicator(swipeState, trigger) },
        onRefresh = onRefresh
    ) {
        if (carros.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                noResultsFound()
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = carros) { carro ->
                    val carroSalvo by remember(leads, carros) {
                        derivedStateOf {
                            carro.id in leads.map(Lead::carroId)
                        }
                    }
                    CarroListItem(
                        modifier = Modifier.animateItemPlacement(),
                        carro = carro,
                        botao = {
                            botaoListItem(carro, carroSalvo)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CarroListItem(
    modifier: Modifier = Modifier,
    carro: Carro,
    botao: @Composable () -> Unit
) {
    var isExpanded by remember {
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
                Logomarca(carro.marcaNome)
                DetalhesCarro(
                    modifier = Modifier.weight(1f, false),
                    carro = carro
                )
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
                    botao()
                }
            }
        }
    }
}


@Composable
private fun DetalhesCarro(
    modifier: Modifier = Modifier,
    carro: Carro
) {
    Column(modifier = modifier) {
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
                carro.combustivel.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }.ifEmpty { stringResource(R.string.nao_informado) }
            )
        )
        Text(
            text = stringResource(
                id = R.string.cor_label,
                carro.cor.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }.ifEmpty { stringResource(R.string.nao_informado) }
            )
        )
        Text(
            text = stringResource(R.string.numero_portas, carro.numeroPortas)
        )
    }
}

@Composable
private fun Logomarca(marca: String) {
    Image(
        modifier = Modifier.size(60.dp),
        painter = getLogomarca(marca),
        contentDescription = stringResource(R.string.cs_logomarca_carro, marca)
    )
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


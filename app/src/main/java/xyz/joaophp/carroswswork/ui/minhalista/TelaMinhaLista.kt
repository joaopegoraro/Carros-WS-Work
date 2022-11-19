package xyz.joaophp.carroswswork.ui.minhalista

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import xyz.joaophp.carroswswork.R
import xyz.joaophp.carroswswork.data.Lead
import xyz.joaophp.carroswswork.ui.CarroViewModel
import xyz.joaophp.carroswswork.ui.components.ListaCarros
import xyz.joaophp.carroswswork.ui.theme.wsPink
import xyz.joaophp.carroswswork.ui.theme.wsWhite

@Composable
fun TelaMinhaLista(
    viewModel: CarroViewModel = getViewModel()
) {
    val state = viewModel.state
    val carros by state.carros.collectAsState(initial = emptyList())
    val leads by state.leads.collectAsState(initial = emptyList())
    val minhaLista by remember(carros, leads) {
        derivedStateOf {
            carros.filter { carro ->
                carro.id in leads.map(Lead::carroId)
            }
        }
    }

    ListaCarros(
        carros = minhaLista,
        leads = emptyList(),
        botaoListItem = { carro, _ ->
            BotaoRemoverFavorito(removerFavorito = {
                viewModel.removerFavoritoCarro(carro)
            })
        }
    )
}

@Composable
fun BotaoRemoverFavorito(removerFavorito: () -> Unit) {
    Button(
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = wsPink),
        onClick = removerFavorito,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_baseline_close_24),
            contentDescription = null,
            tint = wsWhite,
        )
    }
}
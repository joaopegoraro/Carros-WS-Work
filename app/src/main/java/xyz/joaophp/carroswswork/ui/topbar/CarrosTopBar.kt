package xyz.joaophp.carroswswork.ui.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import xyz.joaophp.carroswswork.R

@Composable
fun CarrosTopBar(scaffoldState: ScaffoldState) {
    val drawerScope = rememberCoroutineScope()
    TopAppBar(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
        ) {
            BotaoDrawer(
                modifier = Modifier.align(Alignment.CenterStart),
                abrirDrawer = {
                    drawerScope.launch {
                        if (scaffoldState.drawerState.isOpen) {
                            scaffoldState.drawerState.close()
                        } else {
                            scaffoldState.drawerState.open()
                        }
                    }
                }
            )
            LogoWS(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
private fun BotaoDrawer(
    modifier: Modifier = Modifier,
    abrirDrawer: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = abrirDrawer
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = stringResource(R.string.cs_abrir_drawer)
        )
    }
}

@Composable
private fun LogoWS(modifier: Modifier = Modifier) {
    Box(modifier) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(40.dp),
            painter = painterResource(R.drawable.logows),
            contentDescription = stringResource(R.string.cs_logo_ws)
        )
    }
}

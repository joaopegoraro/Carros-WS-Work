package xyz.joaophp.carroswswork.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import xyz.joaophp.carroswswork.R
import xyz.joaophp.carroswswork.data.Perfil
import xyz.joaophp.carroswswork.ui.CarroViewModel
import xyz.joaophp.carroswswork.ui.navigation.NavigationRoutes
import java.util.regex.Pattern

@Composable
fun CarrosDrawer(
    scaffoldState: ScaffoldState,
    navController: NavController,
    viewModel: CarroViewModel = getViewModel(),
) {
    val perfil = viewModel.state.perfil.collectAsState(initial = null)
    var email by remember(perfil.value?.email) {
        mutableStateOf(perfil.value?.email ?: "")
    }
    var editMode by remember {
        mutableStateOf(false)
    }
    CampoEmail(
        email = email,
        enabled = editMode,
        enable = {
            editMode = it
        },
        onConfirm = {
            val novoPerfil = perfil.value?.copy(email = email) ?: Perfil(email)
            viewModel.atualizarPerfil(novoPerfil)
            editMode = false
        },
        onCancel = {
            email = perfil.value?.email ?: ""
            editMode = false
        },
        onValueChange = {
            email = it
        }
    )
    Divider()
    BotoesDrawer(
        drawerState = scaffoldState.drawerState,
        navController = navController
    )
    Divider()
}

@Composable
private fun CampoEmail(
    email: String,
    enabled: Boolean,
    enable: (Boolean) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val emailValido = !enabled || validarEmail(email)
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (email.isEmpty()) {
            Text(
                modifier = Modifier.padding(3.dp),
                text = stringResource(R.string.cadastre_seu_email),
                fontSize = 11.sp,
            )
        }
        TituloCampo(
            modifier = Modifier.fillMaxWidth(),
            titulo = if (enabled) {
                stringResource(R.string.edite_seu_email)
            } else {
                stringResource(R.string.meus_dados)
            },
            tituloButton = if (enabled) null else stringResource(R.string.editar),
            editar = { enable(true) }
        )
        BasicTextField(
            modifier = if (enabled) {
                Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(5.dp),
                        color = MaterialTheme.colors.onSecondary
                    )
                    .padding(14.dp)
            } else Modifier.fillMaxWidth(),
            value = email,
            enabled = enabled,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onBackground
            ),
            onValueChange = onValueChange,
            decorationBox = { innerTextField ->
                Box {
                    innerTextField()
                    if (email.isEmpty()) {
                        Text(
                            text = stringResource(R.string.escreva_aqui_seu_email),
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onBackground,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        )
        if (!emailValido && email.isNotBlank()) {
            Text(
                modifier = Modifier.padding(3.dp),
                text = stringResource(R.string.email_invalido),
                color = MaterialTheme.colors.error,
                fontSize = 11.sp,
            )
        }
        if (enabled) {
            val buttonModifier = run {
                Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(2.dp))
            }
            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    modifier = buttonModifier
                        .background(MaterialTheme.colors.error)
                        .clickable(onClick = onCancel),
                    painter = painterResource(R.drawable.ic_baseline_close_24),
                    tint = MaterialTheme.colors.onError,
                    contentDescription = "",
                )
                Icon(
                    modifier = buttonModifier
                        .background(MaterialTheme.colors.primary)
                        .clickable(onClick = {
                            if (emailValido && email.isNotBlank()) {
                                onConfirm()
                            }
                        }),
                    painter = painterResource(R.drawable.ic_baseline_check_24),
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "",
                )
            }
        }
    }
}

@Composable
private fun TituloCampo(
    modifier: Modifier,
    titulo: String,
    tituloButton: String? = null,
    editar: (() -> Unit)?
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = titulo,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )

        if (editar != null && tituloButton != null)
            Text(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = editar
                ),
                text = tituloButton,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
    }
}

@Composable
private fun BotoesDrawer(
    drawerState: DrawerState,
    navController: NavController
) {
    val drawerScope = rememberCoroutineScope()
    BotaoDrawer(
        text = stringResource(R.string.carros),
        icon = painterResource(R.drawable.ic_baseline_directions_car_24),
        navigate = {
            navController.navigate(NavigationRoutes.TelaCarros.route) {
                popUpTo(NavigationRoutes.TelaCarros.route) {
                    this.inclusive = true
                }
            }
            drawerScope.launch {
                drawerState.close()
            }
        }
    )
    Divider()
    BotaoDrawer(
        text = stringResource(R.string.minha_lista),
        icon = painterResource(R.drawable.ic_baseline_favorite_24),
        navigate = {
            navController.navigate(NavigationRoutes.TelaMinhaLista.route) {
                popUpTo(NavigationRoutes.TelaCarros.route) {
                    this.inclusive = false
                }
            }
            drawerScope.launch {
                drawerState.close()
            }
        }
    )
}

@Composable
private fun BotaoDrawer(
    text: String,
    icon: Painter,
    navigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = navigate)
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text)
        Icon(icon, contentDescription = null)
    }
}

fun validarEmail(email: String): Boolean {
    val emailRegex = Pattern.compile("^.+@.+\\.[^@]+$")
    return emailRegex.matcher(email).matches()
}

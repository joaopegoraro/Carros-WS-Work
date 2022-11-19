package xyz.joaophp.carroswswork.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object TelaCarros : NavigationRoutes("tela_carros")
    object TelaMinhaLista : NavigationRoutes("tela_minha_lista")
    object TelaContato : NavigationRoutes("tela_contato")
}

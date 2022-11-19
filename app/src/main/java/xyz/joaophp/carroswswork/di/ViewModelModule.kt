package xyz.joaophp.carroswswork.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.joaophp.carroswswork.ui.CarroViewModel

val viewModelModule = module {
    viewModel {
        CarroViewModel(
            carroRepository = get(),
            perfilRepository = get(),
            leadRepository = get()
        )
    }
}
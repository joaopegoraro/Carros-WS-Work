package xyz.joaophp.carroswswork.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.joaophp.carroswswork.ui.ViewModel

val viewModelModule = module {
    viewModel {
        ViewModel(
            carroRepository = get(),
            perfilRepository = get()
        )
    }
}
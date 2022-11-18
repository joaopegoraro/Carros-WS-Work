package xyz.joaophp.carroswswork.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.joaophp.carroswswork.ui.carros.CarrosViewModel

val viewModelModule = module {
    viewModel {
        CarrosViewModel(
            carroRepository = get()
        )
    }
}
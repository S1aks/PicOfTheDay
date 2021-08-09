package ru.s1aks.picoftheday.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.s1aks.picoftheday.model.repository.PODRetrofitImpl
import ru.s1aks.picoftheday.model.repository.Repository
import ru.s1aks.picoftheday.ui.main.MainViewModel
import ru.s1aks.picoftheday.ui.pod_fragment.PODViewModel

val appModule = module {
    single<Repository> { PODRetrofitImpl() }

    viewModel { MainViewModel(get()) }
    viewModel { PODViewModel(get()) }
}
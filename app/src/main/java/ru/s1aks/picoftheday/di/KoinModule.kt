package ru.s1aks.picoftheday.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.s1aks.picoftheday.model.repository.RepositoryImpl
import ru.s1aks.picoftheday.model.repository.Repository
import ru.s1aks.picoftheday.ui.main.MainViewModel
import ru.s1aks.picoftheday.ui.work_list_fragment.WorkListViewModel

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
    viewModel { WorkListViewModel(get()) }
}
package com.entertainment.movieapp.ui.list

import androidx.lifecycle.ViewModel
import com.ccpp.shared.core.di.FragmentScoped
import com.ccpp.shared.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module where classes needed to create the [MainFragment] are defined.
 */
@Module
internal abstract class ListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeListFragment(): ListFragment


    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    internal abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel

}

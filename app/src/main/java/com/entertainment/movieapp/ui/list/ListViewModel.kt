package com.entertainment.movieapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ccpp.shared.core.result.Event
import com.ccpp.shared.entities.ListExample
import com.ccpp.shared.usecase.list.ListUseCase
import com.entertainment.movieapp.base.BaseViewModel
import javax.inject.Inject

class ListViewModel @Inject constructor(
    val listUseCase: ListUseCase
): BaseViewModel()
{

    private val _callGetMovieListEvent = MutableLiveData<Event<ListExample>>()
    val callGetMovieListEvent: LiveData<Event<ListExample>> = _callGetMovieListEvent

    fun getMovieList() {
        loading.postValue(Event(true))
        return listUseCase(Unit) {
            loading.postValue(Event(false))
            it.either(
                ::handleFailure, ::handleMovieList
            )
        }
    }

    private fun handleMovieList(res: ListExample) {
        _callGetMovieListEvent.postValue(Event(res))
    }

}
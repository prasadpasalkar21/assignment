package com.ccpp.shared.usecase.list

import com.ccpp.shared.entities.ListExample
import com.ccpp.shared.network.repository.prefs.ListRepository
import com.ccpp.shared.usecase.UseCase
import javax.inject.Inject

class ListUseCase
@Inject constructor(
    private val repository: ListRepository
) : UseCase<Unit, ListExample>() {

    override suspend fun run(params: Unit) = repository.getList()
}
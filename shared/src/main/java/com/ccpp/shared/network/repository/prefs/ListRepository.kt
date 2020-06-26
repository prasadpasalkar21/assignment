package com.ccpp.shared.network.repository.prefs

import com.ccpp.shared.core.exception.Failure
import com.ccpp.shared.core.result.Either
import com.ccpp.shared.network.ApiService
import com.ccpp.shared.util.NetworkUtils
import com.ccpp.shared.core.result.Result
import com.ccpp.shared.entities.ListExample
import javax.inject.Inject

interface ListRepository {
    fun getList(): Either<Failure, ListExample>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkUtils,
        private val service: ApiService
    ) : ListRepository {

        override fun getList(): Either<Failure, ListExample> {
            return when (networkHandler.hasNetworkConnection()) {
                true -> Result.request(
                    service.getList(),
                    { it.toListRes() },
                    ListExample.empty()
                )
                false -> Either.Left(Failure.NetworkConnection())
            }
        }

    }
}
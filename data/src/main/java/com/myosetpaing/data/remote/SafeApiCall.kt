package com.myosetpaing.data.remote


import com.myosetpaing.data.Constants
import com.myosetpaing.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException
import java.net.ProtocolException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): Result<T> {
    return withContext(dispatcher) {
        try {
            withTimeout(60000) {
                Result.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is ProtocolException -> Result.Error(
                    Constants.SOMETHING_WENT_WRONG_CODE,
                    throwable.localizedMessage ?: "Something Went Wrong"
                )

                is IOException -> Result.Error(
                    Constants.SOMETHING_WENT_WRONG_CODE,
                    throwable.localizedMessage ?: "Something Went Wrong"
                )

                is TimeoutCancellationException -> Result.Error(
                    Constants.SOMETHING_WENT_WRONG_CODE,
                    throwable.localizedMessage ?: "Something Went Wrong"
                )

                is HttpException -> {
                    val code = throwable.code()
                    val errorMsg = throwable.localizedMessage ?: "Something Went Wrong"
                    Result.Error(
                        code,
                        errorMsg
                    )
                }

                else -> {
                    Result.Error(Constants.SOMETHING_WENT_WRONG_CODE, "Something Went Wrong")
                }
            }
        }
    }
}

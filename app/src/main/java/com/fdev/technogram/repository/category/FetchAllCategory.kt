package com.fdev.technogram.repository.category

import com.fdev.technogram.datasource.network.NetworkConst
import com.fdev.technogram.datasource.network.business.abstraction.CategoryNetworkDataSource
import com.fdev.technogram.model.Category
import com.fdev.technogram.model.News
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.FetchRecentNews
import com.fdev.technogram.util.errorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import java.lang.Exception
import javax.inject.Inject

class FetchAllCategory
@Inject
constructor(
        private val categoryNetworkDataSource: CategoryNetworkDataSource
){
    fun fetch(
            dispatcher: CoroutineDispatcher
    ) : Flow<DataState<List<Category>>> = flow{
        try{
            val result = withTimeout(NetworkConst.NETWORK_TIMEOUT ){
                categoryNetworkDataSource.getAllCategories()
            }
           emit(DataState.OnSuccess(data = result , message = "Success"))
        }catch(exception : Exception){
            val errorMessage = errorHandler(exception = exception)
            println(errorMessage)
            emit(DataState.OnFailure<List<Category>>(errorMessage))
        }
    }.flowOn(dispatcher)

}
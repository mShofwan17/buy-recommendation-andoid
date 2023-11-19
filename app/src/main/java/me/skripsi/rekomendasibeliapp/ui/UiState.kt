package me.skripsi.rekomendasibeliapp.ui

import androidx.compose.runtime.Composable

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val message: String? = null
){

    fun showUI(
        onLoading: () -> Unit = {},
        onSuccess: (data: T) -> Unit,
        onError: (message: String) -> Unit
    ){
        if (!isLoading){
            data?.let(onSuccess)
            message?.let(onError)
        }else{
            onLoading.invoke()
        }
    }

    @Composable
    fun showUIComposable(
        onInit: @Composable () -> Unit = {},
        onLoading: @Composable () -> Unit = {},
        onSuccess:@Composable (data: T) -> Unit,
        onError:@Composable (message: String) -> Unit
    ){
        if (!isLoading){
            onInit.invoke()
            data?.let { onSuccess.invoke(it) }
            message?.let{onError.invoke(it)}
        }else{
            onLoading.invoke()
        }
    }
    fun loading() : UiState<T>{
        return this.copy(
            isLoading = true,
            data = null,
            message = null
        )
    }

    fun success(data: T?) : UiState<T>{
        return this.copy(
            isLoading = false,
            data = data,
            message = null
        )
    }

    fun error(message: String?) : UiState<T>{
        return this.copy(
            isLoading = false,
            data = null,
            message = message
        )
    }
}

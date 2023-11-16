package me.skripsi.rekomendasibeliapp.ui

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T?,
    val message: String?
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

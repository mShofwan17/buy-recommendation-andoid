package me.skripsi.data.data_source

open class BaseDateSource {
    suspend fun <T> validateResponse(
        onSuccess: suspend () -> T,
    ): T {
        return try {
            onSuccess()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}
package me.skripsi.rekomendasibeliapp.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.documentfile.provider.DocumentFile
import java.io.File


fun Boolean.viewDiskonLabel(): String {
    return if (this) "Sedang Diskon"
    else "Tidak Diskon"
}

fun Uri.toRealPath(context: Context): String? {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // For Android Q (API 29) and above
        return getRealPathFromDocumentUri(context, this)
    } else {
        // For Android versions below Q
        return getRealPathFromUriBelowQ(context, this)
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun getRealPathFromDocumentUri(context: Context, uri: Uri): String? {
    val contentResolver: ContentResolver = context.contentResolver
    val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
    return cursor?.use {
        val nameIndex: Int = it.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
        it.moveToFirst()
        val fileName: String = if (nameIndex != -1) {
            it.getString(nameIndex)
        } else {
            val uriString: String = uri.toString()
            val name = uriString.substring(uriString.lastIndexOf("/") + 1)
            name
        }
        val file = File(context.cacheDir, fileName)
        try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            file.absolutePath
        } catch (e: Exception) {
            null
        }
    }
}

private fun getRealPathFromUriBelowQ(context: Context, uri: Uri): String? {
    val document = DocumentFile.fromSingleUri(context, uri)
    document?.let {
        if (it.isFile) {
            return it.uri.path
        } else if (it.isDirectory) {
            // Handle directories based on your use case
        }
    }

    // Fallback: Try to get path using MediaStore
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(uri, projection, null, null, null)

    cursor?.use {
        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        if (it.moveToFirst()) {
            return it.getString(columnIndex)
        }
    }

    // Handle other cases as needed
    return null
}
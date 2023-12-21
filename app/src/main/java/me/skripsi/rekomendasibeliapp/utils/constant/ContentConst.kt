package me.skripsi.rekomendasibeliapp.utils.constant

object ContentConst {
    const val WELCOME_HTML = """
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
    <p>
        Halo Pengguna!
        <br><br>
        Untuk menggunakan Aplikasi Rekomendasi Pembelian Barang, langkah pertama yang perlu dilakukan adalah mengimpor data training berbentuk CSV. Ikuti langkah-langkah di bawah ini:
        <br><br>
        1. Pastikan Anda memiliki file data training dalam format CSV.
        <br>
        2. Pilih opsi "Import Data Training" di menu utama aplikasi.
        <br>
        3. Pilih file CSV yang berisi data training Anda.
        <br>
        4. Tunggu sebentar hingga proses impor selesai.
        <br><br>
        Setelah berhasil mengimpor data training, Anda akan dapat menggunakan aplikasi.
        <br><br>
        Terima kasih!
    </p>
</body>

</html>

    """

    const val MESSAGE_DATA_UJI_HTML = """
       <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

    <p>Anda memiliki dua opsi untuk menginput data uji ke dalam aplikasi:</p>
    
   <br>

    <p>
        <strong>1. Import Data Uji dari CSV:</strong>
        Pilih opsi "Import Data Uji" di menu utama. Pilih file CSV yang berisi data uji Anda. Tunggu sebentar hingga proses impor selesai.
    </p>
    
    <br>

    <p>
        <strong>2. Pilih Barang Secara Manual:</strong>
        Pilih opsi "Pilih Barang" di menu utama. Pilih barang dari daftar yang tersedia. Isi formulir dengan detail seperti isDiskon, jumlah stok, dan jumlah penjualan.
    </p>
    
   <br>

    <p>
        Anda dapat memilih salah satu dari dua opsi ini sesuai dengan kebutuhan Anda.
    </p>

   <br>
    
    <p>Terima kasih!</p>

</body>

</html>
    """
}
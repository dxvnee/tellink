package org.d3if3121.tellink.data.model

import com.google.firebase.auth.FirebaseUser

data class Mahasiswa(
    val id: String? = null,
    val nim: String? = null,
    val password: String? = null,
    val nama: String? = null,
    val jurusan: String? = null,
    val angkatan: String? = null,
){
    constructor() : this("","", "", "", "", "")

    companion object {
        const val NIM = "nim"
        const val PASSWORD = "password"
        const val ID = "ID"
        const val NAMA = "nama"
        const val JURUSAN = "jurusan"
        const val ANGKATAN = "angkatan"
    }
}

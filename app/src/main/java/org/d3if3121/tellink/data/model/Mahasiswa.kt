package org.d3if3121.tellink.data.model


data class Mahasiswa(
    val nim: String = "",
    val password: String = "",
    val nama: String = "",
    val jurusan: String? = null,
    val angkatan: String? = null,

    val posts: List<String>? = null,
    val totalpost: Int? = null
){

    constructor() : this("", "", "", "", "", emptyList(), 0)

    companion object {
        const val NIM = "nim"
        const val PASSWORD = "password"
        const val NAMA = "nama"
        const val JURUSAN = "jurusan"
        const val ANGKATAN = "angkatan"
        const val POSTS = "posts"
        const val TOTALPOST = "totalpost"
    }
}

package org.d3if3121.tellink.data.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import org.d3if3121.tellink.data.model.Mahasiswa
import org.d3if3121.tellink.data.model.Response
import org.d3if3121.tellink.data.repository.interfaces.AddMahasiswaResponse
import org.d3if3121.tellink.data.repository.interfaces.DeleteMahasiswaResponse
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListResponse
import org.d3if3121.tellink.data.repository.interfaces.UpdateMahasiswaResponse

class MahasiswaListRepository (
    private val mahasiswaRef: CollectionReference
): MahasiswaListInterface {
    override fun getMahasiswaList() = callbackFlow {
        val listener = mahasiswaRef
            .orderBy("nama")
            .addSnapshotListener { snapshot, e ->
                val mahasiswaListResponse =
                    if (snapshot != null){
                        val mahasiswaList = snapshot.map {
                            it.toMahasiswa()
                        }
                        Response.Success(mahasiswaList)
                    } else {
                        Response.Failure(e)
                    }
                trySend(mahasiswaListResponse)
            }
        awaitClose{
            listener.remove()
        }
    }

    override suspend fun addMahasiswa(mahasiswa: Mahasiswa) = try {

        val mahasiswaSama = mahasiswaRef.whereEqualTo("nim", mahasiswa.nim).get().await()

        if (mahasiswaSama.isEmpty){
            val id = mahasiswaRef.add(mahasiswa).await().id
            Response.Success(id)
        } else {
            Response.Failure(Exception("NIM already registered."))
        }

    } catch (e: Exception){
        Response.Failure(e)
    }

    override suspend fun updateMahasiswa(mahasiswa: Mahasiswa) = try {
        val process = mahasiswa.nim?.let { nim ->
            mahasiswaRef.document(nim).update(mapOf(
                Mahasiswa.NAMA to mahasiswa.nama,
                Mahasiswa.JURUSAN to mahasiswa.jurusan,
                Mahasiswa.ANGKATAN to mahasiswa.angkatan
            )).await()
        }
        Response.Success(process)
    } catch (e: Exception){
        Response.Failure(e)
    }

    override suspend fun deleteMahasiswa(id: String) = try {
        val process = mahasiswaRef.document(id).delete().await()
        Response.Success(process)
    } catch (e: Exception){
        Response.Failure(e)
    }

    override suspend fun getMahasiswaByNim(nim: String) = try {
        val mahasiswa = mahasiswaRef.whereEqualTo("nim", nim).get().await()
        if (mahasiswa.isEmpty){
            Response.Success(mahasiswa.documents[0].toMahasiswa())
        } else {
            Response.Failure(Exception("Mahasiswa Tidak Ditemukan"))
        }
    } catch (e: Exception) {
        Response.Failure(e)
    }

//    override suspend fun getMahasiswaByNim3(nim: String): Mahasiswa? {
//        return try {
//            val document = mahasiswaRef.document(nim).get().await()
//            if (document.exists()) {
//                document.toMahasiswa()
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            null
//        }
//    }

    override suspend fun loginMahasiswa(nim: String, password: String) = try {
        val docmahasiswa = mahasiswaRef.whereEqualTo("nim", nim).get().await()
        if (!docmahasiswa.isEmpty){
            val mahasiswa = docmahasiswa.documents[0].toMahasiswa()

            if (mahasiswa.password == password){
                Response.Success(mahasiswa)
            } else {
                Response.Failure(Exception("Incorrect Password."))
            }
        } else {
            Response.Failure(Exception("NIM doesn't exist."))
        }
    } catch (e: Exception){
        Response.Failure(e)
    }

}

fun DocumentSnapshot.toMahasiswa() = Mahasiswa(
    nama = getString(Mahasiswa.NAMA) ?: "Default",
    password = getString(Mahasiswa.PASSWORD) ?: "Default",
    nim = getString(Mahasiswa.NIM)?: "DefaultName",
    jurusan = getString(Mahasiswa.JURUSAN)?: "DefaultName"
)
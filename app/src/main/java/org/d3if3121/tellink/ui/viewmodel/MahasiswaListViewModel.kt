package org.d3if3121.tellink.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if3121.tellink.data.model.Mahasiswa
import org.d3if3121.tellink.data.model.Response
import org.d3if3121.tellink.data.repository.MahasiswaListRepository
import org.d3if3121.tellink.data.repository.interfaces.AddMahasiswaResponse
import org.d3if3121.tellink.data.repository.interfaces.DeleteMahasiswaResponse
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListResponse
import org.d3if3121.tellink.data.repository.interfaces.UpdateMahasiswaResponse
import javax.inject.Inject

class MahasiswaListViewModel @Inject constructor(
    private val repo: MahasiswaListInterface
): ViewModel() {
    var mahasiswaListResponse by mutableStateOf<MahasiswaListResponse>(Response.Loading)
        private set
    var addMahasiswaResponse by mutableStateOf<AddMahasiswaResponse>(Response.Loading)
        private set
    var updateMahasiswaResponse by mutableStateOf<UpdateMahasiswaResponse>(Response.Loading)
        private set
    var deleteMahasiswaResponse by mutableStateOf<DeleteMahasiswaResponse>(Response.Loading)
        private set


    init {
        getMahasiswaList()
    }

    private fun getMahasiswaList() = viewModelScope.launch {
        repo.getMahasiswaList().collect() {
            mahasiswaListResponse = it
        }
    }

    fun addMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        addMahasiswaResponse = repo.addMahasiswa(mahasiswa)
    }
    fun updateMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        updateMahasiswaResponse = repo.updateMahasiswa(mahasiswa)
    }
    fun deleteMahasiswa(id: String) = viewModelScope.launch {
        deleteMahasiswaResponse = repo.deleteMahasiswa(id)
    }



}
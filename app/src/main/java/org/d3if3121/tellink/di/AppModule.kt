package org.d3if3121.tellink.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.d3if3121.tellink.data.repository.MahasiswaListRepository
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import javax.inject.Singleton

const val MAHASISWA = "mahasiswa"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMahasiswaListRepository(): MahasiswaListInterface = MahasiswaListRepository(
        mahasiswaRef = Firebase.firestore.collection(MAHASISWA)
    )
}
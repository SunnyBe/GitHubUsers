package com.sundayndu.githubusers.di

import android.content.Context
import androidx.room.Room
import com.sundayndu.githubusers.BuildConfig
import com.sundayndu.githubusers.data.cache.GitHubDatabase
import com.sundayndu.githubusers.data.network.NetworkService
import com.sundayndu.githubusers.data.repository.UserRepoImpl
import com.sundayndu.githubusers.data.repository.UserRepository
import com.sundayndu.githubusers.di.qualifiers.IoDispatcher
import com.sundayndu.githubusers.utils.Configs
import com.sundayndu.githubusers.utils.Configs.NETWORK_REQUEST_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        networkService: NetworkService,
        dbService: GitHubDatabase,
        @IoDispatcher appDispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepoImpl(networkService, dbService, appDispatcher)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): GitHubDatabase {
        return Room.databaseBuilder(context, GitHubDatabase::class.java, Configs.DB_NAME)
            .build()
    }

    private fun httpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder()
            .readTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(headerInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun headerInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var original = chain.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("Accept", "application/vnd.github.v3+json")
                    .build()
                original = original.newBuilder().url(url).build()
                return chain.proceed(original)
            }

        }
    }
}
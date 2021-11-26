package com.foodrecipesapp.data

import androidx.room.Index
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource: RemoteDataSource) {

    val remote = remoteDataSource
}
package com.usefi.tmdb.Retrofit

import com.usefi.tmdb.pojo.DetailMoviesModel
import com.usefi.tmdb.pojo.SearchMoviesModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface RetrofitInterface {

    @GET("search/movie?")
    fun getMovie(
        @Query("query") query: String,
        @Query("api_key") api_key: String
    ): Observable<SearchMoviesModel>

    @GET("movie/{movie_id}?")
    fun getDetail(
        @Path("movie_id")itemId : Int,
        @Query("api_key")api_key: String
    ): Observable<DetailMoviesModel>
}

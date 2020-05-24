package com.usefi.tmdb.utils

import com.usefi.tmdb.pojo.*
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

    @GET("genre/movie/list?api_key=")
    fun getGenres(
        @Query("api_key") api_key: String
    ): Observable<Genre>

    @GET("movie/popular?")
    fun getPopulars(
        @Query( "api_key")api_key: String
    ):Observable<PopularsModel>

    @GET("movie/now_playing?")
    fun getNowPlaying(
        @Query("api_key")api_key: String
    ) : Observable<NowPlayingModel>
}


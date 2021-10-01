package com.newsapi.api.service

import com.newsapi.api.*
import com.newsapi.api.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(TOP_HEADLINES)
    suspend fun getTopHeadlinesByCountry(
        @Query(QUERY_COUNTRY) country: String?,
        @Query(QUERY_CATEGORY) category: String?
    ): Response<NewsApiResponse>


}
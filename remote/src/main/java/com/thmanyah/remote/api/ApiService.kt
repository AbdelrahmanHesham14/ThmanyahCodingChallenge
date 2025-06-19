package com.thmanyah.remote.api

import com.thmanyah.remote.model.SectionResponse
import com.thmanyah.remote.model.SectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int
    ): SectionsResponse


    // The search API returns a different data schema than the home sections.
    // For visualization and testing purposes, we’re temporarily using the home section API
    // to simulate search results and verify that the UI and flow behave as expected.
//    @GET("https://mock.apidog.com/m1/735111-711675-default/search")
//    suspend fun search(
//        @Query("query") query: String
//    ): SectionsResponse

    @GET("home_sections")
    suspend fun search(
        @Query("query") query: String
    ): SectionsResponse

}
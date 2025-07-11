// app/src/main/java/com/example/myfirstwishlistapp/api/WishApiService.kt
package com.example.myfirstwishlistapp.api

import com.example.myfirstwishlistapp.models.Wish
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WishApiService {

    // Defines an HTTP GET request to the "wishes/" endpoint relative to the BASE_URL.
    // This function is designed to fetch a list of Wish objects for the main public feed.
    // 'suspend' keyword indicates it's a coroutine function, allowing it to be called from a coroutine scope.
    // 'Response<List<Wish>>' means it expects a Retrofit Response object containing a list of Wish objects.
    @GET("wishes/")
    suspend fun getMainFeed(): Response<List<Wish>>

    // Defines an HTTP GET request to retrieve public and non-completed wishes for a specific user.
    // The '{username}' in the path will be replaced by the value passed to the @Path("username") parameter.
    @GET("users/{username}/wishes/")
    suspend fun getPublicUserWishes(@Path("username") username: String): Response<List<Wish>>

    // Defines an HTTP GET request to retrieve details for a single public and non-completed wish
    // belonging to a specific user.
    // '{username}' and '{id}' in the path are replaced by the corresponding @Path parameters.
    @GET("users/{username}/wishes/{id}/")
    suspend fun getPublicWishDetail(
        @Path("username") username: String,
        @Path("id") wishId: Int
    ): Response<Wish>

    // Example of how you would define other API endpoints:
    // To create a new wish (POST request):
    // @POST("wishes/")
    // suspend fun createWish(@Body wish: WishCreateRequest): Response<Wish>

    // To update an existing wish (PUT request):
    // @PUT("wishes/{id}/")
    // suspend fun updateWish(@Path("id") wishId: Int, @Body wish: WishUpdateRequest): Response<Wish>

    // To delete a wish (DELETE request):
    // @DELETE("wishes/{id}/")
    // suspend fun deleteWish(@Path("id") wishId: Int): Response<Unit> // Unit for no response body
}

// app/src/main/java/com/example/myfirstwishlistapp/MainActivity.kt
package com.example.myfirstwishlistapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myfirstwishlistapp.api.RetrofitClient
import kotlinx.coroutines.launch

// NEW: Import the data classes from your models package
import com.example.myfirstwishlistapp.models.Wish
import com.example.myfirstwishlistapp.models.User
import com.example.myfirstwishlistapp.models.Tag


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity" // Tag for Logcat messages

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to your XML layout file (e.g., activity_main.xml)
        // This connects your Kotlin code to your UI layout defined in XML.
        setContentView(R.layout.activity_main)

        // Example of fetching data from your Django API
        // This call will be made when the activity is created.
        fetchMainFeed()
    }

    /**
     * Fetches the main public wish feed from the Django API.
     * This function uses Kotlin Coroutines for asynchronous network operations.
     */
    private fun fetchMainFeed() {
        // lifecycleScope is a CoroutineScope tied to the Activity's lifecycle.
        // launch starts a new coroutine.
        lifecycleScope.launch {
            try {
                // Make the API call using the Retrofit client instance.
                val response = RetrofitClient.instance.getMainFeed()

                // Check if the API call was successful and the response body is not null.
                if (response.isSuccessful && response.body() != null) {
                    val wishes = response.body()
                    // Log the success and the number of wishes fetched.
                    Log.d(TAG, "Fetched ${wishes?.size} wishes from main feed.")

                    // TODO: Update your UI (e.g., a RecyclerView or TextView) with the fetched wishes.
                    // For example, if you had a TextView with ID 'my_text_view' in activity_main.xml:
                    // val textView: TextView = findViewById(R.id.my_text_view)
                    // textView.text = "Fetched ${wishes?.size} wishes: ${wishes?.joinToString { it.title }}"

                    // If you want to see the titles in Logcat for debugging:
                    wishes?.forEach { wish ->
                        // 'wish.title', 'wish.id', and 'wish.user.username' should now be resolved
                        Log.d(TAG, "Wish: ${wish.title} (ID: ${wish.id}) by ${wish.user.username}")
                    }

                } else {
                    // Log an error if the API call was not successful.
                    Log.e(TAG, "Error fetching main feed: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                // Catch any network-related exceptions (e.g., no internet, host unreachable).
                Log.e(TAG, "Network error fetching main feed: ${e.message}", e)
            }
        }
    }

    // You would add more functions here for other API calls and UI interactions, such as:
    /*
    private fun fetchPublicUserWishes(username: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getPublicUserWishes(username)
                if (response.isSuccessful && response.body() != null) {
                    val wishes = response.body()
                    Log.d(TAG, "Fetched ${wishes?.size} wishes for user $username.")
                    // Update UI with user-specific wishes
                } else {
                    Log.e(TAG, "Error fetching user wishes: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Network error fetching user wishes: ${e.message}", e)
            }
        }
    }

    private fun fetchPublicWishDetail(username: String, wishId: Int) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getPublicWishDetail(username, wishId)
                if (response.isSuccessful && response.body() != null) {
                    val wish = response.body()
                    Log.d(TAG, "Fetched detail for wish: ${wish?.title}")
                    // Update UI with single wish details
                } else {
                    Log.e(TAG, "Error fetching wish detail: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Network error fetching wish detail: ${e.message}", e)
            }
        }
    }
    */
}

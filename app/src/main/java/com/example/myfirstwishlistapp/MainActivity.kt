// app/src/main/java/com/example/myfirstwishlistapp/MainActivity.kt
package com.example.myfirstwishlistapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myfirstwishlistapp.api.RetrofitClient
import kotlinx.coroutines.launch

// REMOVED: @file:Suppress("UNUSED_PARAMETER") or any Compose annotations at the top

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to your XML layout file (e.g., activity_main.xml)
        setContentView(R.layout.activity_main)

        // Example of fetching data from your Django API
        fetchMainFeed()
    }

    private fun fetchMainFeed() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getMainFeed()
                if (response.isSuccessful && response.body() != null) {
                    val wishes = response.body()
                    Log.d(TAG, "Fetched ${wishes?.size} wishes from main feed.")
                    // TODO: Update your UI (e.g., a RecyclerView or TextView) with the fetched wishes
                    // Example: If you had a TextView with ID 'my_text_view' in activity_main.xml
                    // val textView: TextView = findViewById(R.id.my_text_view)
                    // textView.text = "Fetched ${wishes?.size} wishes: ${wishes?.joinToString { it.title }}"
                } else {
                    Log.e(TAG, "Error fetching main feed: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Network error fetching main feed: ${e.message}", e)
            }
        }
    }

    // REMOVED: All @Composable functions and related Compose preview code
    // For example, if you had:
    // @Composable
    // fun Greeting(name: String, modifier: Modifier = Modifier) {
    //     Text(text = "Hello $name!", modifier = modifier)
    // }
    // @Preview(showBackground = true)
    // @Composable
    // fun DefaultPreview() {
    //     MyFirstWishlistAppTheme {
    //         Greeting("Android")
    //     }
    // }
}

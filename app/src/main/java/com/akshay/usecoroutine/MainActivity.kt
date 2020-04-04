package com.akshay.usecoroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

/**
 * count on background thread,
 * showing progress on progress bar and
 * update the UI post completion
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* GlobalScope.launch {
             (1..10).forEach { count ->
                 delay(2000)
                 // ERROR - Only the original thread that created a view hierarchy can touch its views.
                 // we tried to access the textView from background thread
                 textView.text = count.toString()
             }
         }*/

        GlobalScope.launch(Dispatchers.Main) {
            textView.text = "Downloading"
            progressBar2.visibility = View.VISIBLE
            calculateProgress(100)
            textView.text = "Downloaded"
            progressBar2.visibility = View.GONE
        }
    }

    private suspend fun calculateProgress(input: Int) {
        withContext(Dispatchers.Default) {
            (0..input step 10).forEach { count ->
                delay(1000)
                runOnUiThread {
                    progressBar.progress = count
                }
            }
        }
    }
}

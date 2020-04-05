package com.akshay.usecoroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akshay.usecoroutine.database.SampleDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

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

        /*GlobalScope.launch(Dispatchers.Main) {

            updateText("Downloading") // ui thread

            progressBar2.visibility = View.VISIBLE // ui thread

            val result = calculateProgress(100) // worker thread - suspend till the work get finish

            updateText(result) // ui thread

            progressBar2.visibility = View.GONE // ui thread
        }*/

        val dataSource = SampleDatabase.getInstance(application).sampleDao
        val viewModelFactory = SampleViewModelFactory(dataSource, application)
        val sampleViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(SampleViewModel::class.java)

        sampleViewModel.sampleData.observe(this, Observer {
            textView.text = "Data from Database -> " + it.toString()
        })
    }

    private suspend fun calculateProgress(input: Int): String =
        withContext(Dispatchers.IO) {
            (0..input step 10).forEach { count ->
                delay(1000)
                runOnUiThread {
                    updateProgressBar(count)
                }
            }
            "Downloaded"
        }

    private fun updateText(lable: String) {
        textView.text = lable
    }

    private fun updateProgressBar(count: Int) {
        progressBar.progress = count
    }
}

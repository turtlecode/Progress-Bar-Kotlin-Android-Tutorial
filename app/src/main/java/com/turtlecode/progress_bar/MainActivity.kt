package com.turtlecode.progress_bar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var i = 0
    private var txtView: TextView? = null
    private val handler = android.os.Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar
        txtView = findViewById<TextView>(R.id.text_view)

        val btn = findViewById<Button>(R.id.show_button)

        btn.setOnClickListener {
            progress_Bar.visibility = View.VISIBLE
            i = progress_Bar.progress
            Thread(Runnable {
                val name    = name.text.toString()
                val surname = surname.text.toString()
                val message = "Hey ${name} ${surname}"
                while (i < 100) {
                    i += 1
                    handler.post(Runnable {
                        progress_Bar.progress = i
                        txtView!!.text = i.toString() + "/" + progress_Bar.max
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                val intent = Intent(this, MainActivity2::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, message)
                }
                startActivity(intent)

                progress_Bar.visibility = View.INVISIBLE
            }).start()

        }
    }
}
























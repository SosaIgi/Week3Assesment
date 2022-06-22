package com.example.week3assesment

import android.app.Dialog
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        val client =  OkHttpClient()
        lateinit var btnPlay : Button
        lateinit var btnPause : Button
        var mediaPlayer : MediaPlayer? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)


        btnPlay.setOnClickListener{
            playAudio()
        }
        btnPause.setOnClickListener{
            pauseAudio()
        }

        setUpTabBar()
        checkConnection()

    }

    }

    private fun playAudio() {
        val audioUrlRock = "https://itunes.apmediaPlayer!!.setDataSource(audioUrlRock)ple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
        val audioUrlPop = "https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"
        val audioUrlClassic = "https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50"
        var mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer!!.setDataSource(audioUrlRock)
            mediaPlayer!!.setDataSource(audioUrlClassic)
            mediaPlayer!!.setDataSource(audioUrlPop)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(this, "audio started Playing", Toast.LENGTH_SHORT).show()
    }

       private fun pauseAudio() {
            var mediaPlayer = MediaPlayer()
            if (mediaPlayer!!.isPlaying) {

                mediaPlayer!!.stop()
                mediaPlayer!!.reset()
                mediaPlayer!!.release()
            } else {
                Toast.makeText(this, "Audio has not played", Toast.LENGTH_SHORT).show
            }
        }
    private fun checkConnection(applicationContext: Any) {
        val manager = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (null != networkInfo){
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this,"Wifi Connected", Toast.LENGTH_LONG).show()
            }
            else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this,"Mobile Data Connected", Toast.LENGTH_LONG).show()
            }
        }
        else{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_alert_dialog)

            dialog.setCanceledOnTouchOutside(false)

            dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

          dialog.btn_try_again.setOnClickListner{
              recreate()
          }
            dialog.show()
        }
    }

    private fun setUpTabBar() {
        val adapter = TabPageAdapter(this, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object: ViewPager2.onPageChangeCallback(){
            override fun onPageSelected(position: Int){
                tabLayout.seleceTab(tabLayout.getTabAt(position))
            }
        })
    tablayout.addOnTabSelectedListner(object: TabLayout.onTabSelectedListner{
        override fun onTabSelected(tab: TabLayout.Tab){
            viewPager.currentItem = tab.position
        }
        override fun onTabSelected(tab: TabLayout.Tab?){}
        override fun onTabUnselected(tab: TabLayout.Tab?){}
    })


    }





}



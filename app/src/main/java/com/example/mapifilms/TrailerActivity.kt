package com.example.mapifilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import java.util.*

class TrailerActivity : YouTubeBaseActivity(){

    lateinit var youtube:YouTubePlayerView
    lateinit var videoId:String
    lateinit var btnPlay:Button

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    val APIKEY="AIzaSyBjLjcL2YfX6dcVdSMg2iG-8tUzY2DbBHY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)

        youtube =findViewById(R.id.playerYou)
        btnPlay= findViewById(R.id.btnPlay)


        videoId=recogerUrl()


        youtubePlayerInit= object:YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
               p1?.loadVideo(videoId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Fallo", Toast.LENGTH_SHORT).show()
            }

        }
        btnPlay.setOnClickListener {
            youtube.initialize(APIKEY,youtubePlayerInit)
        }
    }

    private fun recogerUrl(): String {
        var lista :List<String> =intent.getStringExtra("trailer").toString().split("=")
        var cuantos:Int= lista.size
        return lista.get(cuantos-1)
    }
}
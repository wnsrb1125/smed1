package com.laugh.smed1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.*
import kotlin.concurrent.schedule
import android.content.DialogInterface
import android.graphics.Color
import android.widget.*
import androidx.appcompat.app.AlertDialog
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity(),View.OnClickListener {

    var time: TextView? = null
    var timerTask: TimerTask? = null
    var timer = Timer()
    var idvalue = 0;
    var mediaplayer : MediaPlayer? = null
    var inse : String = "0"
    var count : Int = 0
    lateinit var mad : InterstitialAd;
    lateinit var b1 : ImageButton;
    lateinit var b2 : ImageButton;
    lateinit var b3 : ImageButton;
    lateinit var b4 : ImageButton;
    lateinit var b5 : ImageButton;
    lateinit var b6 : ImageButton;
    lateinit var b7 : ImageButton;
    lateinit var b8 : ImageButton;
    lateinit var b9 : ImageButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this);
        mad = InterstitialAd(this)
        mad.adUnitId = "ca-app-pub-3940256099942544/1033173712"

        b1 = findViewById<ImageButton>(R.id.b1)
        b2 = findViewById<ImageButton>(R.id.b2)
        b3 = findViewById<ImageButton>(R.id.b3)
        b4 = findViewById<ImageButton>(R.id.b4)
        b5 = findViewById<ImageButton>(R.id.b5)
        b6 = findViewById<ImageButton>(R.id.b6)
        b7 = findViewById<ImageButton>(R.id.b7)
        b8 = findViewById<ImageButton>(R.id.b8)
        b9 = findViewById<ImageButton>(R.id.b9)
        time = findViewById<TextView>(R.id.time)
        var play = findViewById<Button>(R.id.play)


        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
        b9.setOnClickListener(this)
        play.setOnClickListener(this)
        time!!.setOnClickListener(this)
        inse = this.getResources().getString(R.string.insecond)
        time!!.text = inse

    }


    private fun startTimerTask() {

        timerTask = object : TimerTask() {
            internal var count = Integer.parseInt(time!!.text as String)
            override fun run() {
                if(count == 0)
                    count++
                count--
                time?.post(Runnable { time!!.setText("$count") })
                if(count == 0) {
                    stopTimerTask()
                }
            }
        }
        timer.schedule(timerTask, 0, 1000)
    }

    private fun stopTimerTask() {
        if (timerTask != null) {
            timerTask!!.cancel()
            timerTask = null
        }
    }


    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    override fun onClick(v: View?) {
        if (v?.id == R.id.play) {
            when(idvalue) {
                R.id.b1 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b1)
                }
                R.id.b2 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b2)
                }
                R.id.b3 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b3)
                }
                R.id.b4 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b4)
                }
                R.id.b5 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b5)
                }
                R.id.b6 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b6)
                }
                R.id.b7 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b7)
                }
                R.id.b8 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b8)
                }
                R.id.b9 -> {
                    mediaplayer = MediaPlayer.create(this, R.raw.b9)
                }
            }
            if(time!!.text == this.getResources().getString(R.string.insecond)) {
                time!!.text = "0"
            }
            startTimerTask()
            var delay:Long = (time!!.text as String).toLong() * 1000
            if(delay < 0 )
                delay = 0
            Timer().schedule(delay) {
                mediaplayer!!.start()
            }
            count++
            if (count == 4) {
                mad.loadAd(AdRequest.Builder().build())
                mad.show()
                count = 0
            }
        }
        else if(v?.id == R.id.time) {
            stopTimerTask()
            val aDialog = AlertDialog.Builder(this@MainActivity)
            var edittext:EditText = EditText(this@MainActivity)
            val toastm : String = this.getResources().getString(R.string.second)
            edittext.setInputType(0x00000002)
            aDialog.setTitle(toastm)
            aDialog.setView(edittext)
            aDialog.setPositiveButton("enter", DialogInterface.OnClickListener { dialog, which ->
                val value = edittext.getText().toString()
                time!!.setText(value)
            })
            aDialog.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> })
            val ad = aDialog.create()
            ad.show()
        }
        else {
            b1.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b2.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b3.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b4.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b5.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b6.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b7.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b8.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            b9.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape)
            v?.setBackgroundColor(Color.YELLOW)
            idvalue = v?.id!!
        }

    }
    
   
}

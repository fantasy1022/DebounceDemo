package com.fantasy1022.debouncedemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.android.schedulers.AndroidSchedulers
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private var times: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        normalNextBtn.setOnClickListener {
            playTimesTxt.setText(times++.toString())
        }

        //throttleFirst
        //throttleLast
        //debounce
        //sample
        RxView.clicks(rxViewNextButton)
                .throttleLast(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  playTimesTxt.setText(times++.toString()) }

        
    }
}

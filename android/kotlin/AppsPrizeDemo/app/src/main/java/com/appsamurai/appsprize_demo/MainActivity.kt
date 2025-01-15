package com.appsamurai.appsprize_demo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.appsamurai.appsprize.AppReward
import com.appsamurai.appsprize.AppsPrize
import com.appsamurai.appsprize.AppsPrizeListener
import com.appsamurai.appsprize.AppsPrizeNotification
import com.appsamurai.appsprize.config.AppsPrizeConfig
import com.appsamurai.appsprize_demo.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = AppsPrizeConfig.Builder()
            .setLanguage("en")
            .setCountry("US")
            .build(
                APPS_PRIZE_APP_TOKEN,
                ADVERTISING_ID,
                USER_ID
            )
        AppsPrize.initialize(applicationContext, config, object : AppsPrizeListener {
            override fun onInitialize() {
                Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onInitialize")
                Toast.makeText(this@MainActivity, " AppsPrize:onInitialize", Toast.LENGTH_SHORT).show()
            }

            override fun onInitializeFailed(errorMessage: String) {
                Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onInitializeFailed: err: $errorMessage")
                Toast.makeText(this@MainActivity, " AppsPrize:onInitializeFailed:${errorMessage}", Toast.LENGTH_SHORT).show()
            }

            override fun onRewardUpdate(rewards: List<AppReward>) {
                Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onRewardUpdate: $rewards")
                Toast.makeText(this@MainActivity, " AppsPrize:onRewardUpdate:${rewards}", Toast.LENGTH_SHORT).show()
            }

            override fun onNotification(notifications: List<AppsPrizeNotification>) {
                Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onNotification: $notifications")
                Toast.makeText(this@MainActivity, " AppsPrize:onNotification:${notifications}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.offersButton.setOnClickListener {
            val available = AppsPrize.launchActivity(this@MainActivity)
            if (!available) {
                Log.e("[AppsPrize]", "AppsPrize not initialized")
            }
        }

        /**
         * used for handling session reward without AppsPrizeListener.onRewardUpdate event method
         * AppsPrize.doReward(this) { sessionReward ->
         *
         * }
        */
    }
}
package com.appsamurai.appsprize_compose_demo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.appsamurai.appsprize.AppReward
import com.appsamurai.appsprize.AppsPrize
import com.appsamurai.appsprize.AppsPrizeListener
import com.appsamurai.appsprize.AppsPrizeNotification
import com.appsamurai.appsprize.config.AppsPrizeConfig
import com.appsamurai.appsprize_compose_demo.ui.theme.AppsPrizeComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val config = AppsPrizeConfig.Builder()
            .setLanguage("en")
            .setCountry("US")
            .build(
                APPS_PRIZE_APP_TOKEN,
                ADVERTISING_ID,
                USER_ID
            )

        AppsPrize.initialize(
            applicationContext,
            config,
            object : AppsPrizeListener {
                override fun onInitialize() {
                    Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onInitialize")
                }

                override fun onInitializeFailed(errorMessage: String) {
                    Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onInitializeFailed: err: $errorMessage")
                }

                override fun onRewardUpdate(rewards: List<AppReward>) {
                    Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onRewardUpdate: $rewards")
                }

                override fun onNotification(notifications: List<AppsPrizeNotification>) {
                    Log.d("[AppsPrize]", "MainActivity:onCreate AppsPrize:onNotification: $notifications")
                }
            }
        )

        enableEdgeToEdge()
        setContent {
            AppsPrizeComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        Modifier.padding(innerPadding).fillMaxSize()
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .align(Alignment.Center),
                            onClick = {
                                AppsPrize.launchActivity(this@MainActivity)
                            }
                        ) {
                            Text("Open AppsPrize")
                        }
                    }
                }
            }
        }
    }
}

package com.app.mqtt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.*


class MainActivity : AppCompatActivity() {

    private lateinit var mqttHelper:MqttHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mqttHelper = MqttHelper(applicationContext)
        startMqtt()

        btn_pub.setOnClickListener {
            if (btn_pub.text.toString() == "ON") {
                mqttHelper.publishToTopic("1")
                btn_pub.text = "OFF"
            } else {
                mqttHelper.publishToTopic("0")
                btn_pub.text = "ON"
            }
        }
    }

    fun startMqtt() {
        mqttHelper.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(reconnect: Boolean, serverURI: String?) {

            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                txt_message.text = "Status : " + message.toString()
            }

            override fun connectionLost(cause: Throwable?) {

            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {

            }

        })
    }

}

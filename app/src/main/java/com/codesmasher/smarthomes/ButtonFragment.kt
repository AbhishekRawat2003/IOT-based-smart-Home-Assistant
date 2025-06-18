
package com.codesmasher.smarthomes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import java.io.IOException
import java.util.Calendar
import android.os.*
class ButtonFragment : Fragment() {

    private lateinit var toggleLight: ToggleButton
    private lateinit var toggleFan: ToggleButton
    private lateinit var btnSetBuzzerTime: Button
    private val handler = Handler(Looper.getMainLooper())



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button,container,false)

        toggleLight = view.findViewById<ToggleButton>(R.id.toggle_light)
        toggleFan = view.findViewById<ToggleButton>(R.id.toggle_fan)
        btnSetBuzzerTime = view.findViewById<Button>(R.id.btn_set_buzzer_time)
//        val toggleSocket = view.findViewById<ToggleButton>(R.id.toggle_socket)


        toggleLight.setOnCheckedChangeListener { _, isChecked ->
            sendCommandToBluetooth(if (isChecked) "turn on light" else "turn off light")
        }

        toggleFan.setOnCheckedChangeListener { _, isChecked ->
            sendCommandToBluetooth(if (isChecked) "turn on fan" else "turn off fan")
        }

        btnSetBuzzerTime.setOnClickListener {
            showDateTimePicker()
        }

//
//        toggleSocket.setOnCheckedChangeListener { _, isChecked ->
//            sendCommandToBluetooth(if (isChecked) "socket on" else "socket off")
//        }

        return view

    }

    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(requireContext(), { _, year, month, day ->
            TimePickerDialog(requireContext(), { _, hour, minute ->
                calendar.set(year, month, day, hour, minute, 0)
                val delay = calendar.timeInMillis - System.currentTimeMillis()

                if (delay > 0) {
                    handler.postDelayed({
                        sendCommandToBluetooth("buzzer on")

                        // Optional: Turn off after 10 seconds
                        handler.postDelayed({
                            sendCommandToBluetooth("buzzer off")
                        }, 10_000)
                    }, delay)

                    Toast.makeText(context, "Buzzer scheduled", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Invalid time", Toast.LENGTH_SHORT).show()
                }

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }


    private fun sendCommandToBluetooth(command: String) {
        val socket = (activity as MainActivity).bluetoothSocket
        if (socket != null) {
            try {
                socket.outputStream.write(command.toByteArray())
                socket.outputStream.flush()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Failed to send command", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Bluetooth not connected", Toast.LENGTH_SHORT).show()
        }
    }
}

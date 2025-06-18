package com.codesmasher.smarthomes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import java.util.*

class VoiceCommandFragment : Fragment(){
    private val SPEECH_REQUEST_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startVoiceInput()
        return inflater.inflate(R.layout.fragment_voice_command, container, false)
    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your command...")
        try {
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Voice not supported", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val command = result?.get(0)?.lowercase()

            val mainActivity = activity as? MainActivity
            when {
                command?.contains("turn on light") == true -> {
                    mainActivity?.sendCommand("turn on light")
                    Toast.makeText(context, "Turning on light", Toast.LENGTH_SHORT).show()
                }
                command?.contains("turn off light") == true -> {
                    mainActivity?.sendCommand("turn off light")
                    Toast.makeText(context, "Turning off light", Toast.LENGTH_SHORT).show()
                }
                command?.contains("turn on fan") == true -> {
                    mainActivity?.sendCommand("turn on fan")
                    Toast.makeText(context, "Turning on fan", Toast.LENGTH_SHORT).show()
                }
                command?.contains("turn off fan") == true -> {
                    mainActivity?.sendCommand("turn off fan")
                    Toast.makeText(context, "Turning off fan", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Unknown command", Toast.LENGTH_SHORT).show()
                }
            }

            parentFragmentManager.popBackStack() // Go back to previous screen
        }
    }
}


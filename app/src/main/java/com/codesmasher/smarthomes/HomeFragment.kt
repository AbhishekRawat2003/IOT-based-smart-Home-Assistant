package com.codesmasher.smarthomes
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home,container,false)
        val alarmButton: Button = view.findViewById(R.id.button_alarm)
        val voiceButton: Button = view.findViewById(R.id.btn_voice)
        val btnBtn : Button = view.findViewById(R.id.btn_btn)
        val btnVoice = view.findViewById<Button>(R.id.btn_voice)




        alarmButton.setOnClickListener {
            Toast.makeText(requireContext(), "Opening Alarm Settings...", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AlarmFragment())
                .addToBackStack(null)
                .commit()
        }
        voiceButton.setOnClickListener {
            Toast.makeText(requireContext(), "opening voice commands...", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container, VoiceCommandFragment()).addToBackStack(null)
                .commit()
        }
        btnBtn.setOnClickListener {
          Toast.makeText(context,"Button CLicked!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container,
                ButtonFragment()).commit()
        }
        btnVoice.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container,
                VoiceCommandFragment()).addToBackStack(null).commit()
        }
        return view
    }

}

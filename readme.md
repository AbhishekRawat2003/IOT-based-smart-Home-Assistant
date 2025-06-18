# 🏠 IoT-Based Smart Home Automation

This project focuses on building a smart home automation system using IoT technology. It allows you to monitor and control electrical appliances through the Blynk mobile application or web dashboard using ESP32 or NodeMCU microcontrollers.
An Android + Arduino-based smart home assistant project that allows users to control home appliances using Bluetooth, buttons, and voice commands.

## 🔧 Features

- Remote control of appliances via smartphone (Blynk)
- Temperature and humidity monitoring using DHT11
- Automatic light control using LDR sensor
- Motion detection for security using PIR sensor
- Real-time status updates on the app
- Wi-Fi-based control (no need for physical switches)

## 🛠️ Tech Stack

- **Microcontroller:** ESP32 / NodeMCU (ESP8266)
- **Cloud/App:** Blynk IoT Platform
- **Sensors:** DHT11, PIR, LDR, Relay Module
- **Programming Language:** C++ (Arduino IDE)

## 📲 How It Works

1. ESP32 reads data from sensors.
2. Data is sent to the Blynk app via Wi-Fi.
3. User can control devices and monitor values in real-time.
4. Relay module switches ON/OFF appliances based on app or sensor logic.
   
### Android App:

1. Open in Android Studio.
2. Connect a phone or emulator.
3. Build and run.
4. Pair with HC-05 via Bluetooth settings.
5. Use app to control devices.

### Arduino:

1. Upload `first.ino` to Arduino Uno using Arduino IDE.
2. Connect HC-05 to RX/TX, and relays to pins as per code.


## 🚀 Getting Started

1. Install the Blynk IoT app.
2. Flash the code to your ESP32/NodeMCU.
3. Connect sensors and relays as per the diagram.
4. Start monitoring and controlling your home remotely.

## 📜 License

free to use and modify.

---

## 👨‍💻 Author

**Abhishek Rawat**  
[GitHub Profile](https://github.com/AbhishekRawat2003)

# 🏠 IoT-Based Smart Home Automation

This project focuses on building a smart home automation system using IoT technology. It allows you to monitor and control electrical appliances through the Blynk mobile application or web dashboard using ESP32 or NodeMCU microcontrollers.

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

## 📷 Demo & Circuit Diagram

![Circuit Diagram](images/circuit-diagram.png)
![App Screenshot](images/app-screenshot.png)

## 🚀 Getting Started

1. Install the Blynk IoT app.
2. Flash the code to your ESP32/NodeMCU.
3. Connect sensors and relays as per the diagram.
4. Start monitoring and controlling your home remotely.

## 📚 License

This project is licensed under the MIT License.

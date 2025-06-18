#include "BluetoothSerial.h"

BluetoothSerial SerialBT;

#define RELAY_LIGHT 26
#define RELAY_FAN 27
#define RELAY_DEVICE 14
#define BUZZER 12

void setup() {
  Serial.begin(115200);
  SerialBT.begin("SMARTHOME");
  Serial.println("Bluetooth Started!");

  pinMode(RELAY_LIGHT, OUTPUT);
  pinMode(RELAY_FAN, OUTPUT);
  pinMode(RELAY_DEVICE, OUTPUT);
  pinMode(BUZZER, OUTPUT);

  digitalWrite(RELAY_LIGHT, LOW);
  digitalWrite(RELAY_FAN, LOW);
  digitalWrite(RELAY_DEVICE, LOW);
  digitalWrite(BUZZER, LOW);

  Serial.println("Bluetooth Relay Control Started");
}

void loop() {
  if (SerialBT.available()) {
    // Serial.write(SerialBT.read()); // This line might cause issues.
    String cmd = SerialBT.readStringUntil('\n');
    cmd.trim();
    cmd.toLowerCase();

    Serial.print("Received command: ");
    Serial.println(cmd);

    // LIGHT
    if (cmd == "turn on light" || cmd == "light on") {
      digitalWrite(RELAY_LIGHT, HIGH);
      Serial.println("Light ON");
    } else if (cmd == "turn off light" || cmd == "light off") {
      digitalWrite(RELAY_LIGHT, LOW);
      Serial.println("Light OFF");
    }

    // FAN
    else if (cmd == "turn on fan" || cmd == "fan on") {
      digitalWrite(RELAY_FAN, HIGH);
      Serial.println("Fan ON");
    } else if (cmd == "turn off fan" || cmd == "fan off") {
      digitalWrite(RELAY_FAN, LOW);
      Serial.println("Fan OFF");
    }

    // DEVICE
    else if (cmd == "turn on device" || cmd == "device on") {
      digitalWrite(RELAY_DEVICE, HIGH);
      Serial.println("Device ON");
    } else if (cmd == "turn off device" || cmd == "device off") {
      digitalWrite(RELAY_DEVICE, LOW);
      Serial.println("Device OFF");
    }

    // BUZZER
    else if (cmd == "buzzer on") {
      digitalWrite(BUZZER, HIGH);
      Serial.println("Buzzer ON");
    } else if (cmd == "buzzer off") {
      digitalWrite(BUZZER, LOW);
      Serial.println("Buzzer OFF");
    }

    else {
      Serial.println("Unknown command: " + cmd);
    }
  }
}

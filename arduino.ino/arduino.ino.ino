#include <Servo.h>

Servo servoX;
Servo servoY;

void setup() {
  servoX.attach(10);
  servoY.attach(9);
  Serial.begin(9600);

}

void loop() {
  if (Serial.available() > 0) {

    String data = Serial.readStringUntil('\n');
    data.trim();

    if (data.startsWith("mov")) {
      int x, y;
      if (sscanf(data.c_str(), "mov %d %d", &x, &y) == 2) {

        servoX.write(constrain(x, 0, 180));
        servoY.write(constrain(y, 0, 180));
      }
    }
  }
}

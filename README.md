# Smart-Door-Lock 

## group name: KROM

## Group members
* Ameni MBTIBAA  (ameni.mtibaa17@gmail.com)  .[A_MTIBAA](https://github.com/amenimtibaa "A_MTIBAA")
* André OBROCHTA  (andreobrochta@gmail.com) .[A_OBROCHTA](https://github.com/aobrochta "A_OBROCHTA")
* Lucky RAHERINIAINA  (raheriluc@gmail.com) .[L_RAHERINIAINA](https://github.com/raheriluc "L_RAHERINIAINA")
* Adlane KADRI  (adlan68@live.fr) .[A_KADRI](https://github.com/adlaneKadri "A_KADRI")

-----------------------------------------------------------------------------------------------------------------------------------

## Project name: SMART DOOR 

#### Idea: 
a smart door will open automatically if it recognizes the person standing in front of it.
In the opposite case, if it doesn't, a notification is sent to the owner device who can choose whether to allow the person to enter the house or not.

#### Proposed Versions: 
* 1st-Version :
The first version is a simple one :
if a person wants to enter and is recognized, the door open automatically (in our case a led lights up green).
If the person is foreign (a led lights up red), a notification is sent to the owner of the house who can accept or refuse to open the door (also, he has the possibility to add this person in the list of people that the device can recognize)
the idea of this project resembles the project of august smart lock (But the challenge is to make it work with low cost equipment!). 
here is a useful link to explain:  https://www.youtube.com/watch?v=WMln85LNENo

* 2nd-Version : 
the purpose of the second version is to improve the security part of the device.
For example:
Someone can put a picture of a person in front of the camera and this person is recognized by the door.
So we have to detect a real person from pictured one.

* 3rd-Version :
The last version we can propose is still based on improving the security part( because a 100% secure device is the real challenge)
So here's another example:
A thief can force a person to stand in front of the door because that person can be recognized by the door. 
So the idea is to open the door based on a model of emotions recognition .


#### Framework:
* ESP-WHO: is a face detection and recognition platform that is currently based on Espressif Systems' ESP32 chip
* TensorFlow: is an open source machine learning framework.

#### Library : 
* Open-cv :an open Source Computer Vision Library specialized in real-time image processing.


## List of essential components:

ESP32-Wrover: 
     a powerful, generic WiFi-BT-BLE MCU module that targets a wide
     variety of applications, ranging from low-power sensor networks to the most demanding tasks, 
     such as voice encoding, music streaming and MP3 decoding.
ov2640 camera:
	2 Megapixels,supports image sizes: scaling down from SXGA to 40×30, image quality controls including [color saturation, gamma, sharpness, lens correction, white pixel canceling, noise canceling, and 50/60 Hz luminance detection]

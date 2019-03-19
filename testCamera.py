import requests as rq
import numpy as np 
import cv2 as cv 


url = "http://192.168.1.42:8080/shot.jpg"
detector = cv.CascadeClassifier('haarcascade_frontalface_default.xml')

while True:
	pic_resp = rq.get(url)
	pic_array = np.array(bytearray(pic_resp.content),dtype=np.uint8)
	img = cv.imdecode(pic_array, -1)

	#define the screen resulation
	img_scaled = cv.resize(img,None,fx= 0.4,fy= 0.4)
	gray = cv.cvtColor(img_scaled, cv.COLOR_BGR2GRAY)
	faces = detector.detectMultiScale(gray, scaleFactor=1.5, minNeighbors=5)
	for (x,y,z,w) in faces:
		print(x,y,z,w)
		pic_gray = gray [y:y+w , x:x+z]
		pic_color = img_scaled[y:y+w, x:x+z]
		colorRectangle = (141, 250, 205)
		cv.rectangle(img_scaled, (x,y) , (x+z,y+w) ,colorRectangle, 3)  

	cv.imshow("camera",img_scaled)

	if cv.waitKey(100) & 0xFF == ord('x'):
		break

import requests as rq
import numpy as np 
import cv2 as cv 


url = "http://192.168.1.42:8080/shot.jpg"

while True:
	pic_resp = rq.get(url)
	pic_array = np.array(bytearray(pic_resp.content),dtype=np.uint8)
	img = cv.imdecode(pic_array, -1)

#define the screen resulation
	img_scaled = cv.resize(img,None,fx= 0.5,fy= 0.5)

	cv.imshow("camera",img_scaled)

	if cv.waitKey(100) & 0xFF == ord('x'):
		break

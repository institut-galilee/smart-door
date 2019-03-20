import os
from PIL import Image 
import numpy as np 
import cv2 as cv
import pickle
currentPath = os.path.dirname(os.path.abspath(__file__))
print(currentPath)
#to import all image in the filder "images"
dirContent = os.path.join(currentPath, "dataBase")

xTraining= []
yTraining= []
detector = cv.CascadeClassifier('haarcascade_frontalface_default.xml')
label_ids= {}
cpt = 1  
model = cv.face.LBPHFaceRecognizer_create()

for root, dirs, files in os.walk(dirContent):
	for file in files:
		if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
			#get the path of each picture
			imgPath=os.path.join(root,file)

			#get to name of the person (diroctory) of each picture
			dirName= os.path.basename(os.path.dirname(imgPath))
			lowerDirName = os.path.basename(os.path.dirname(imgPath)).replace(" ","-").lower()  #

			#print(imgPath)
			#print(dirName)
			#print(lowerDirName)
			#xTraining.append(imgPath)
			#yTraining.append(lowerDirName)
			""" 
				treat data, use the path  to get the image, after reading  the image  we use 
				the numpy lib to change or read this image as an array to start the trainner, 
				because we have  to use  informations of  images as numbers to learn about it 
			"""
			pillowImage = Image.open(imgPath).convert("L") #covert to grayScale  before (mode = RGB) now (mode = L)
			#print(pillowImage)
			imgTOarray = np.array(pillowImage, "uint8")
			#print(imgTOarray)
			faces = detector.detectMultiScale(imgTOarray, scaleFactor=1.5, minNeighbors=5)
			if not lowerDirName in label_ids :
				label_ids[lowerDirName]= cpt
				cpt =cpt+1 
			id_ = label_ids[lowerDirName]

			for(x,y,z,w) in faces: 
				#print(x,y,z,w)
				faceData = imgTOarray[y:y+w ,x:x+z]
				faceLabel = id_
				xTraining.append(faceData)
				yTraining.append(faceLabel)
				#print(faceLabel)

#print(label_ids)
with open("labels.pickle",'wb') as f :
	pickle.dump(label_ids, f) 
	

model.train(xTraining,np.array(yTraining))
model.save("trainner.yml")

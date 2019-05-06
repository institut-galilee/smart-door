import face_recognition
from PIL import Image, ImageDraw 
import os

#The current Path 
currentPath = os.path.dirname(os.path.abspath(__file__))
#comming dir contains pictures taken for people who're in front of the camera
dirPath = os.path.join(currentPath, "comming")
dirPathSavingFaces = os.path.join(currentPath, "toTreat")
f = []
for root, dirs, files in os.walk(dirPath):
	for file in files:
		if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
			imgPath =os.path.join(root,file)
			image = face_recognition.load_image_file(imgPath)
			face_locations = face_recognition.face_locations(image, model="cnn")
			for face_location in face_locations:
			    # Print the location of each face in this image
			    top, right, bottom, left = face_location
			    getFaceLocation = image[top:bottom, left:right]
			    facePILL = Image.fromarray(getFaceLocation)
			    #generate a number to differentiate between the images we are going to save
			    index = (top*14+right*17)/21 + left*7/5 + bottom*5/10 
			    index = int(index) 
			    facePILL.save(f'toTreat/{index}.jpg')
			    

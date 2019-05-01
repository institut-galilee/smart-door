import face_recognition
from PIL import Image, ImageDraw 
import os
import pandas as pd 
import random
#The current Path 
currentPath = os.path.dirname(os.path.abspath(__file__))
#comming dir contains pictures taken for people who're in front of the camera
dirPath = os.path.join(currentPath, "comming")
dirPathSavingFaces = os.path.join(currentPath, "toTreat")
filelist = [ f for f in os.listdir(dirPathSavingFaces) if f.endswith("png") or f.endswith("jpg") or f.endswith("jpeg")  ]
for f in filelist:
    os.remove(os.path.join(dirPathSavingFaces, f))

for root, dirs, files in os.walk(dirPath):
	for file in files:
		if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
			#print(os.path.join(root,file))
			imgPath =os.path.join(root,file)
			image = face_recognition.load_image_file(imgPath)
			face_locations = face_recognition.face_locations(image)
			for face_location in face_locations:

			    # Print the location of each face in this image
			    top, right, bottom, left = face_location
			    #print("A face is located at pixel location Top: {}, Left: {}, Bottom: {}, Right: {}".format(top, left, bottom, right))

			    getFaceLocation = image[top-20:bottom+20, left-20:right+20]
			    facePILL = Image.fromarray(getFaceLocation)
			    #facePILL.show()
			    index = random.randint(1,19061995)
			    rangeLetters='adlanekadriADLANEKADRI19061995'
			    pictSavedNames = ''.join((random.choice(rangeLetters) for cpt in range(len(str(index)))))+ "." +'jpg'
			    
			    #facePILL.save(f'toTreat/{pictSavedNames}.jpg')
			    saveRoot = os.path.join(currentPath, "toTreat")
			    savePath =os.path.join(saveRoot,pictSavedNames)
			    facePILL.save(savePath)
			   				   

							#""" RECOGNITION """#			    

#The current Path 
currentPath = os.path.dirname(os.path.abspath(__file__))
#connu dir contains pictures taken for known people 
dirPathConnu = os.path.join(currentPath, "connu")
dataBase = []
names= []

# get faces of people we know
for root, dirs, files in os.walk(dirPathConnu):
	for file in files:
		if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
			imgPath =os.path.join(root,file)
			imageKnown = face_recognition.load_image_file(imgPath)
			#print(len(face_recognition.face_encodings(imageKnown)))
			fileEncoded = face_recognition.face_encodings(imageKnown)[0]
			dataBase.append(fileEncoded)
			names.append(os.path.splitext(file)[0])
			
dirPath = os.path.join(currentPath, "toTreat")
dataBase2 = []

# get faces in front of caméra 
for root, dirs, files in os.walk(dirPath):
	for file in files:
		if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
			#print(os.path.join(root,file))
			imgPath =os.path.join(root,file)
			imageKnown = face_recognition.load_image_file(imgPath)
			fileEncoded = face_recognition.face_encodings(imageKnown)[0]
			dataBase2.append(fileEncoded)

recognized= []
for pictEncoded in dataBase2: 
	response = face_recognition.compare_faces(dataBase, pictEncoded)
	listResult= pd.Series(response)
	#print(s[s].index.values)
	for val in listResult[listResult].index.values :
		if names[val] not in recognized : 
			recognized.append(names[val])

# List of recognized person, it will be sent to the database			
print(recognized)

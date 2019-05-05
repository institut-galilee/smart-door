import zeroSMS as sms 
import face_recognition
from PIL import Image, ImageDraw 
import os
import pandas as pd 
import random
import mysql.connector 
from mysql.connector import Error
from mysql.connector import errorcode

def mysqlConnection():
	import mysql.connector
	from mysql.connector import Error
	try:
	    connection = mysql.connector.connect(host='127.0.0.1',
	                             database='smartdoor',
	                             user='root',
	                             password='biza')
	except Error as e :
	    print ("Error while connecting to MySQL", e)
	finally:
	    #closing database connection.
	    if(connection.is_connected()):
	        return connection;

def insert(recognizedOrNOT,namePerson):
	connection = mysqlConnection()
	cursor = connection.cursor()
	querySELECT = "SELECT * FROM notification"
	cursor.execute(querySELECT)
	r = cursor.fetchall()
	idUSER = cursor.rowcount +1 
	sql_insert_query = """ INSERT INTO `notification`(`id`, `knownValues`, `personName`)
	 VALUES (%s,%s,%s)"""
	insert_tuple = (idUSER,recognizedOrNOT,namePerson)
	cursor.execute(sql_insert_query,insert_tuple)
	print("{} inserted".format(namePerson))
	connection.commit()      


def recognition():
	#The current Path 
	currentPath = os.path.dirname(os.path.abspath(__file__))
	#comming dir contains pictures taken for people who're in front of the camera
	dirPath = os.path.join(currentPath, "comming")
	dirPathSavingFaces = os.path.join(currentPath, "toTreat")
	filelist = [ f for f in os.listdir(dirPathSavingFaces) if f.endswith("png") or f.endswith("jpg") or f.endswith("jpeg")  ]
	detected = False 
	for f in filelist:
	    os.remove(os.path.join(dirPathSavingFaces, f))

	for root, dirs, files in os.walk(dirPath):
		for file in files:
			if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
				#print(os.path.join(root,file))
				imgPath =os.path.join(root,file)
				image = face_recognition.load_image_file(imgPath)
				face_locations = face_recognition.face_locations(image)
				if len(face_locations)!=0 and detected == False:
				    	detected = True
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


	if detected : 
		#The current Path 
		currentPath = os.path.dirname(os.path.abspath(__file__))
		#connu dir contains pictures taken for known people 
		dirPathConnu = os.path.join(currentPath, "connu")
		dataBase = []
		names= []

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

		print(recognized)
		if(len(recognized)==0):
			insert(0,"unknown")
			subject = "WARNING"
			msg = "Attention! \n il y a un inconnu devant la porte"
		else: 
			subject = "IFORMATION"
			a = "these person have returned: \n"
			b = "this person has returned: \n"
			msg = "We could detect that %s "%(a if len(recognized)>1 else b)  
			for personName in recognized:
				insert(1,personName) 
				msg = msg + "- " + personName + "\n"			
		sms.sendingTEXT(subject, msg)
	else : 
		print("any person detected")

if __name__ == '__main__':
	recognition()


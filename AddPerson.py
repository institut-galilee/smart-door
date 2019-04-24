import cv2 as cv
import numpy as np 
import os
import smtplib
import config
from getpass import getpass
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage
from email.mime.base import MIMEBase
import pickle as pk 




def catchingFace():	
	cam = cv.VideoCapture(0)
	#Face classifier //front of face will be detected using the next function
	detectorFace = cv.CascadeClassifier('haarcascade_frontalface_default.xml')
	detectorEyes = cv.CascadeClassifier('haarcascade_eye.xml')
	detectorSmile = cv.CascadeClassifier('haarcascade_smile.xml')
	model = cv.face.LBPHFaceRecognizer_create()
	model.read("trainner.yml")
	labelsName = {}
	notName = True
	i = 1
	while notName :
		personName= input("put your name:  ")
		pathDir = "dataBase/"+personName
		if not os.path.exists(pathDir):
			os.mkdir(pathDir)
			print("Directory " , personName ,  " Created ")
			notName = False
		else : 
			print("Directory " , personName ,  " already exists")
			yesORno= input(" you want to use it: Y/N")
			if yesORno == "y" : 
				notName = False
				currentPath = os.path.dirname(os.path.abspath(__file__))
				dirContent = os.path.join(currentPath, pathDir)
				for root, dirs, files in os.walk(dirContent):
					for file in files:
						if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
							i = i + 1

			
	

	with open("labels.pickle",'rb') as file : 

		labelsName= {	persons:id_s for id_s , persons in pk.load(file).items()}	
	print(labelsName)
	a = 1    
	while(True):

	    ret, picture = cam.read()

	    #we gonna use gray because the face classifier detecte the multi scale of a gray image 	
	    gray = cv.cvtColor(picture, cv.COLOR_BGR2GRAY)
	    #detecte the face of the person front of the camera
	    faces = detectorFace.detectMultiScale(gray, scaleFactor=1.5, minNeighbors=5)
	    #recuperate the 4 points of the rectagle of the faces
	    colorRectangleFace = (141, 250, 205)
	    colorRectangleEyes = (102, 150, 105)
	    colorRectangleSmile = (14, 50, 205)
	    colorName = (220, 100, 225)

	    for (x,y,z,w) in faces:
		   	#print(x,y,z,w)
		   	pic_gray  = gray [y:y+w, x:x+z]
		   	pic_color = picture[y:y+w, x:x+z]

		   	pictureGETTING = picture[y-60:y+w+60, x-60:x+z+60]
		   	label ,confidence = model.predict(pic_gray)
		   	font = cv.FONT_HERSHEY_SIMPLEX
		   	if a == 1 : 
		   		name = "push right"	
		   	if a == 2 :
		   		name = "push left"
		   	if a == 3 :
		   		name = "be smiling"
		   	if a == 4 :
		   		name = "close your mouth" 
		   	if a == 5 :
		   		name = "close your eyes"
		   		
		   	if cv.waitKey(100) & 0xFF == ord ('a'):
		   		a=a+1
		   		pwdName_picture= pathDir+"/"+str(i)+".png"
		   		print(pwdName_picture)
		   		cv.imwrite(pwdName_picture, pictureGETTING)
		   		i = i + 1
		   		if a > 5 : 
		   			a=1


		   		
		   		
		    	    
	    	#cv.imwrite(pwdName_picture,loc_gray)
		   	cv.putText(picture, name, (x,y), font, 1 , colorName, 1, cv.LINE_AA)
		   	cv.rectangle(picture, (x,y) , (x+z,y+w) ,colorRectangleFace, 1)
		   	#print("shape is:".cv.imread("qq.jpg")[0].shape)
		   	eyes =  detectorEyes.detectMultiScale(gray)
		   	for (x,y,z,w) in eyes:
		   		cv.rectangle(picture, (x,y),(x+z,y+w),colorRectangleEyes , 1 ) 
					
		    
	    cv.imshow('frame',picture)
	    if cv.waitKey(100) & 0xFF == ord('x'):
	        break
	        
	cap.release()
	cv.destroyAllWindows()

"""
def SendMail(ImgFileName):
    img_data = open(ImgFileName, 'rb').read()
    msg = MIMEMultipart()
    msg['Subject'] = 'subject'
    msg['From'] = 'e@mail.cc'
    msg['To'] = 'e@mail.cc'

    text = MIMEText("test")
    msg.attach(text)
    image = MIMEImage(img_data, name=os.path.basename(ImgFileName))
    msg.attach(image)

    s = smtplib.SMTP(Server, Port)
    s.ehlo()
    s.starttls()
    s.ehlo()
    s.login(UserName, UserPassword)
    s.sendmail(From, To, msg.as_string())
    s.quit()
""" 

def sendingTEXT(subject, msg):
    try: 
        server = smtplib.SMTP('smtp.gmail.com:587')
        server.ehlo()
        server.starttls()
        EmailSender= "smartkey19061995@gmail.com"
        Password="smartkey123456789"
        EmailReceiver="kadriadan@gmail.com"
        m ="wahiba.boudjou93@gmail.com"
            #server.login(config.EMAIL_ADDRESS, config.PASSWORD)
        server.login(EmailSender, Password)
        
        message = 'Subject: {}\n\n{}'.format(subject, msg)
            #server.sendmail(config.EMAIL_ADDRESS, config.EMAIL_ADDRESS, message)
        server.sendmail(EmailSender, EmailReceiver, message)
        server.quit()
        print("great: Email sent!")
    except:
        print("failed :/")

        
catchingFace()

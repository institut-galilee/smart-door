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

def catching():	
	cam = cv.VideoCapture(0)
	#Face classifier //front of face will be detected using the next function
	detector = cv.CascadeClassifier('haarcascade_frontalface_default.xml')



	   
	while(True):

	    ret, picture = cam.read()

	    #we gonna use gray because the face classifier detecte the multi scale of a gray image 	
	    gray = cv.cvtColor(picture, cv.COLOR_BGR2GRAY)
	    #detecte the face of the person front of the camera
	    faces = detector.detectMultiScale(gray, scaleFactor=1.5, minNeighbors=5)
	    #recuperate the 4 points of the rectagle of the faces
	    	
	    for (x,y,z,w) in faces:
	    	print(x,y,z,w)
	    	pic_gray  = gray [y:y+w, x:x+z]
	    	pic_color = picture[y:y+w, x:x+z]

	    	#Model for face recognization, y+w

	    	pwdName_picture= "faceAdlane.png"
	    	#cv.imwrite(pwdName_picture,loc_gray)
	    	colorRectangle = (141, 250, 205)
	    	cv.rectangle(picture, (x,y) , (x+z,y+w) ,colorRec, 3)

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

catching()

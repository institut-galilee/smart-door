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
        #Password= getpass("Tap your password: ")
        EmailReceiver="ameni.mtibaa17@gmail.com"
            #server.login(config.EMAIL_ADDRESS, config.PASSWORD)
        server.login(EmailSender, Password)
        
        message = 'Subject: {}\n\n{}'.format(subject, msg)
            #server.sendmail(config.EMAIL_ADDRESS, config.EMAIL_ADDRESS, message)
        server.sendmail(EmailSender, EmailReceiver, message)
        server.quit()
        print("great: Email sent!")
    except:
        print("failed :/")



subject = "WARNING"
msg = "Attention! \n il y a un inconnu devant la porte"
sendingTEXT(subject, msg)





"""
def sendPicture(subject, msg, FileName):
    try:
        img_data = open(ImgFileName, 'rb').read()
        msg = MIMEMultipart()
        server = smtplib.SMTP('smtp.gmail.com:587')
        server.ehlo()
        server.starttls()
        EmailSender= "smartkey19061995@gmail.com"
        Password="smartkey123456789"
        EmailReceiver="kadriadan@gmail.com"
        #server.login(config.EMAIL_ADDRESS, config.PASSWORD)
        server.login(Email, Password)
        message = 'Subject: {}\n\n{}'.format(subject, msg)
        #server.sendmail(config.EMAIL_ADDRESS, config.EMAIL_ADDRESS, message)
        server.sendmail(EmailSender,EmailReceiver, Password)
        server.quit()
        print("Success: Email sent!")
    except:
        print("Email failed to send.")
"""
def SendMail(ImgFileName):
    img_data = open(ImgFileName, 'rb').read()
    msg = MIMEMultipart()
    EmailSender= "smartkey19061995@gmail.com"
    psw = getpass("Type your password and press enter: ")
    Password="smartkey123456789"
    EmailReceiver="adlan68@live.fr"
        
    msg['Subject'] = 'subject'
    msg['From'] = EmailSender
    msg['To'] = EmailReceiver

    text = MIMEText("test")
    msg.attach(text)
    image = MIMEImage(img_data, name=os.path.basename(ImgFileName))
    msg.attach(image)

    s = smtplib.SMTP(Server, Port)
    s.ehlo()
    s.starttls()
    s.ehlo()
    s.login(EmailReceiver, Password)
    s.sendmail(EmailSender, EmailReceiver, msg.as_string())
    s.quit()

imageName = "images/adlane/adaan.png"
#SendMail(imageName)    

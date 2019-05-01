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

import smtplib
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
        Password="**********"
        #Password= getpass("Tap your password: ")
        EmailReceiver="raheriluc@gmail.com"
        #adlan68@live.fr,raheriluc@gmail.com,andreobrochta@gmail.com, ameni.mtibaa17@gmail.com
           #server.login(config.EMAIL_ADDRESS, config.PASSWORD)
        server.login(EmailSender, Password)
        message = 'Subject: {}\n\n{}'.format(subject, msg)
            #server.sendmail(config.EMAIL_ADDRESS, config.EMAIL_ADDRESS, message)
        server.sendmail(EmailSender, EmailReceiver, message)
        server.quit()
        print("great: Email sent!")
    except:
        print("failed :/")



#subject = "WARNING"
#msg = "Attention! \n il y a un inconnu devant la porte"



if __name__ == '__main__':
    # test1.py executed as script
    # do something
    sendingTEXT(subject, msg)
import cv2 as cv
cam = cv.VideoCapture(0)
#detector=cv.CascadeClassifier('haarcascade_frontalface_default.xml')



while(cam.isOpened()):
    ret, frame = cam.read()

    gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)

    cv.imshow('frame',gray)
    if cv.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv.destroyAllWindows()

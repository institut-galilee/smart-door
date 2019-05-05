import face
import time 
import os
import shutil
import random

def start():
	while True:
		cut()
		face.recognition()
		time.sleep(1)
		drop()
def cut():
	currentPath = os.path.dirname(os.path.abspath(__file__))
	dirPath = os.path.join(currentPath, "download")
	dirPathSaving = os.path.join(currentPath, "comming")
	filelist = [ f for f in os.listdir(dirPath) if f.endswith("png") or f.endswith("jpg") or f.endswith("jpeg")  ]
	for f in filelist : 
		source =os.path.join(dirPath,f)
		shutil.move(source, dirPathSaving)
		index = random.randint(1,19061995)
		rangeLetters='adlanekadriADLANEKADRI19061995'
		newNames = ''.join((random.choice(rangeLetters) for cpt in range(len(str(index)))))+ "." +'jpg'
		#print(newNames)
		src =os.path.join(dirPathSaving,f)
		dst=os.path.join(dirPathSaving,newNames)
		os.rename(src, dst)

def drop():
	currentPath = os.path.dirname(os.path.abspath(__file__))
	dirPath = os.path.join(currentPath, "comming")
	filelist = [ f for f in os.listdir(dirPath) if f.endswith("png") or f.endswith("jpg") or f.endswith("jpeg")  ]
	for f in filelist : 
		os.remove(os.path.join(dirPath, f))
start()

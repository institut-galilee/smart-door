import os 

path = os.path.dirname(os.path.abspath(__file__))
print(path)
#to import all image in the filder "images"
img = os.path.join(path, "images")

xTraining= []
yTraining= []
for root, dirs, files in os.walk(img):
	for file in files:
		if file.endswith("png") or file.endswith("jpg") or file.endswith("jpeg"):
			#get the path of each picture
			imgPath=os.path.join(root,file)

			#get to name of the person (diroctory) of each picture
			dirName= os.path.basename(os.path.dirname(imgPath))
			print(imgPath)
			print(dirName)
			xTraining.append(imgPath)
			yTraining.append(dirName)

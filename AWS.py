import boto3 

#The two faces we're gonna compare
inputFace = "8.png"
targetFace = "9.png"

#config the region -in our account id use us-east-2
boto3.setup_default_session(region_name='us-east-2')

#use The client rekognition for face comparison 
client = boto3.client('rekognition',
         aws_access_key_id='AKIAUG6IPVRCXTEO72GB',
         aws_secret_access_key= '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')

#picture to base64, because we gonna use local files
with open(inputFace, "rb") as image_file:
     input_Bytes = image_file.read()

with open(targetFace, "rb") as image_file:
     target_Bytes = image_file.read()
#lunch the function of face comparison
AWSresponse = client.compare_faces(
	SourceImage={
	'Bytes': input_Bytes
	},
	TargetImage={
	'Bytes': target_Bytes
	}
)
faceMatches_ =AWSresponse['FaceMatches']
faceUnMatches_ = AWSresponse['UnmatchedFaces']

#display result of comparison between the two faces
if len(faceUnMatches_)==0:
	faceInformations = faceMatches_
	for information in faceInformations:
		faceSimilarity = information
		confidence = information['Face']
		print("Recognized with {}""%"" confidence ".format(confidence['Confidence']))

else :
	faceInformations = faceUnMatches_
	for information in faceInformations:
		confidence = information['Confidence']
		print("Non recognized with {}""%"" confidence ".format(confidence))
		


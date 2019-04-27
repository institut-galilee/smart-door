import boto3 

boto3.setup_default_session(region_name='us-east-2')
client = boto3.client('rekognition',
         aws_access_key_id='AKIAUG6IPVRCXTEO72GB',
         aws_secret_access_key= '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')

with open("8.png", "rb") as image_file:
     input_Bytes = image_file.read()

with open("9.png", "rb") as image_file:
     target_Bytes = image_file.read()

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
#print(AWSresponse)
if len(faceUnMatches_)==0:
	faceInformations = faceMatches_
	for information in faceInformations:
		faceSimilarity = information
		confidence = information['Face']
		#print("Matched With {}""%"" Similarity ".format(faceSimilarity['Similarity']))
		print("Recognized with {}""%"" confidence ".format(confidence['Confidence']))

else :
	faceInformations = faceUnMatches_
	for information in faceInformations:
		confidence = information['Confidence']
		print("Non recognized with {}""%"" confidence ".format(confidence))
		#print(information['Confidence'])


# Gender Detector App
## Endpoints:
GET 
/detectGender

Example inputs:
* adam magda ALL TOKENS
* michał text FIRST TOKEN

GET /getTokens 
* No input needed, it will return zip file with packed txt files with male and female names.



Male list is taken from: [here](https://dane.gov.pl/pl/dataset/1667,lista-imion-wystepujacych-w-rejestrze-pesel-osoby-zyjace/resource/28104/table?page=1&per_page=20&q=&sort=)

Female list is taken from: [here](https://dane.gov.pl/pl/dataset/1667,lista-imion-wystepujacych-w-rejestrze-pesel-osoby-zyjace/resource/28103/table?page=1&per_page=20&q=&sort=)

To build Docker Image:

docker build -f Dockerfile -t detector:1 .

To run Docker image

docker run -p 8080:8080 detector:1

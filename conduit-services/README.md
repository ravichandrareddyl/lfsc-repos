# conduit-services

This is starter code for node-js applications.

## Installation

###Build Docker image

#### dev
```sh
$ ./dockerTask.sh build debug
```
#### release
```sh
$ ./dockerTask.sh build release
```

###Spun up docker container

#### run code for testing local. This would install nodemon for self restart
```sh
$ ./dockerTask.sh compose debug
```

#### release
```sh
$ ./dockerTask.sh compose release
```
#### stop running containers in local
```sh
$ ./dockerTask.sh stop debug
```

#### check unittest coverage
```sh
$ npm run coverage
```

#### Run unit tests
```sh
$ npm run test
```

### APIs

#### send sms
```sh
$ curl -X POST \
  http://localhost:5858/sendSms \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"toPhone": "919000000001",
	"smsText": "Hi there!"
}'
```
'use strict';

const express = require('express');
const bodyParser = require('body-parser');
const { helloWorld, searchDoctors, sendSms}  = require('./routes');
const { shutDown } = require('./utils');
// Constants
const PORT = 8080;
const HOST = '0.0.0.0';

// App
const app = express();
app.use(bodyParser.urlencoded({
    extended: true
}));

app.use(bodyParser.json());
app.get('/', helloWorld);
app.post('/searchDocs', searchDoctors);
app.post('/sendSms', sendSms)

app.listen(PORT, HOST);

process.on('SIGTERM', shutDown);
process.on('SIGINT', shutDown);

console.log(`Running on http://${HOST}:${PORT}`);
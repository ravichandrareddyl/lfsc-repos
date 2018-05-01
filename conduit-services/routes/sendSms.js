'use strict';
const R = require('rambda'),
    rp = require('request-promise'),
    smsUrl = 'http://www.businesssms.co.in/SMS.aspx',
    validateUtil = require('../utils/validUtil'),
    defaultParams = {
        ID: process.env.smsId || 'testId',
        Pwd: process.env.smsPassword || 'testPassword',
    },
    sendSms = (req, res) => {
        const toPhone = req.body.toPhone,
            smsText = req.body.smsText,
            validation = validateUtil(req.body, ['toPhone', 'smsText']);
             
            if (validation.hasErrors) {
                return Promise.resolve(res.status(400).send({errors: validation.errors}));
            }
        
            const options = {
                uri: smsUrl, 
                qs: R.merge(defaultParams, {PhNo: toPhone, Text: smsText}),
                headers: {
                    'User-Agent': 'Request-promise'
                }
            };
        return rp(options)
            .then(data => res.send(data))
            .catch(err => res.status(500).send({errors: [{errorType: 'Interval Server Error', errorMsg: err}]}))

    };

module.exports = sendSms;


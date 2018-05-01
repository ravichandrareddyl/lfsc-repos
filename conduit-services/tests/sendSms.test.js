'use strict';

const R = require('rambda'),
    test = require('tape').test,
    proxyquire = require('proxyquire'),
    sinon = require('sinon'),
    prepareSmsSendModule = (condition) => {
        const res = {
            status: sinon.spy(function() {
                return this;
            }),
            send: sinon.spy(function(response) {
                return response;
            })
        },
        rp = sinon.spy(function() {
            return condition ? Promise.resolve('success') : Promise.reject('error');
        }),
        stubs = {
            'request-promise': rp
        };
        return {
            sendSms: proxyquire('../routes/sendSms', stubs),
            res,
            rp
        }
    }

test('should respond with error incase of missing inputs', t => {
    const req = {
            body: {}
        },
        {sendSms, res, rp} = prepareSmsSendModule(true);
    
    t.plan(1);

    return sendSms(req, res)
        .then(dat => {
            t.deepEqual(dat, {errors: [ { errorType: 'Required', errorMsg: 'toPhone is missing in Request body' }, { errorType: 'Required', errorMsg: 'smsText is missing in Request body' } ]});
        })
        .catch(err => {
            t.fail('Failed incase of error');
        })
});


test('should respond with success incase valid inputs', t => {
    const req = {
            body: {
                toPhone: '9190000000001',
                smsText: 'Hi there'
            }
        },
        options = {
            uri: 'http://www.businesssms.co.in/SMS.aspx',
            qs: {
                ID: 'testId',
                Pwd: 'testPassword',
                PhNo: req.body.toPhone, 
                Text: req.body.smsText
            },
            headers: {
                'User-Agent': 'Request-promise'
            }
        },
        {sendSms, res, rp} = prepareSmsSendModule(true);
    
    t.plan(2);

    return sendSms(req, res)
        .then(dat => {
            t.assert(rp.calledWith(options), 'called with input params');
            t.equal(dat, 'success', 'should be success response')
        })
        .catch(err => {
            t.fail('Failed incase of error');
        })
});
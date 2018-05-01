'use strict';

const test = require('tape').test,
    proxyquire = require('proxyquire'),
    prepareValidationModule = () => {
        return {validationUtil: proxyquire('../utils/validUtil', {})}
    }

test('should validate valid object', t => {
    const validUtil = prepareValidationModule().validationUtil,
        inpObj = {a: 1, b: 2},
        requiredParams = ['a', 'b'];

    t.deepEqual(validUtil(inpObj, requiredParams), {hasErrors: false, errors: []});
    t.end();
});

test('should validate invalid object', t => {
    const validUtil = prepareValidationModule().validationUtil,
        inpObj = {a: 1, b: 2},
        requiredParams = ['a', 'b', 'c'],
        output = validUtil(inpObj, requiredParams);

    t.equal(output.hasErrors, true);
    t.deepLooseEqual(output.errors[0], {errorType: 'Required', errorMsg: 'c is missing in Request body'});
    t.end();
})
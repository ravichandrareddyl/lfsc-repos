'use strict';

const R = require('rambda'),
    validate = (obj, requireParams) => {
        const errors = R.map(param => {
            if(!R.has(param, obj)) {
                return {errorType: 'Required', errorMsg: `${param} is missing in Request body`}
            }
        }, requireParams)
        .filter(validError => !R.isNil(validError));

        return {hasErrors: R.length(errors) > 0, errors}
    };

module.exports = validate;
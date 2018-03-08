'use strict';
const R = require('rambda');
const connPool = require('../config/dbConnection');
const shutDown = (cb) => {
    connPool.end(function (err) {
        if(R.is(Function, cb)) {
            cb();
        }
      });
}

module.exports = shutDown;
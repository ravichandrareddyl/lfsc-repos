'use strict';
const R = require('rambda');
const pool = require('../config/dbConnection');
const executeQuery = (sql, args) => {
    return new Promise( ( resolve, reject ) => {
        pool.query( sql, args, ( err, rows ) => {
            if ( err )
                return reject( err );
            resolve( rows );
        } );
    });
}
module.exports = {
    executeQuery: executeQuery
}
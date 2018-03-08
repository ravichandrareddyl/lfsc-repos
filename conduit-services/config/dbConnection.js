'use strict';

const R = require('rambda');
const mysql = require('mysql');
const defaultConObj = require('../dbConfig');
const dbConnObj = R.merge(defaultConObj, {
        connectionLimit: 10,
        host: process.env.dbHost || defaultConObj.dbHost,
        user: process.env.user || defaultConObj.user,
        password: process.env.password || defaultConObj.password,
        database: process.env.database || defaultConObj.database  
    });

module.exports  = mysql.createPool(dbConnObj);

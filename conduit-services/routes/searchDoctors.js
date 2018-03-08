'use strict';

const { executeQuery } = require('../utils/dbUtil');
const queries = require('../constants/queries.json');
const searchDoctors = (req, res) => {
    return executeQuery(queries.searchDocs)
        .then(rows => res.send(rows))
        .catch(err => {
            res.status(500).send(JSON.stringify(err))
        });
}
module.exports = searchDoctors;
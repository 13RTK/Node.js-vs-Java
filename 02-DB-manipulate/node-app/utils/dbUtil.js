import mysql from "mysql";
import DBConfig from "../config/dbConfig.js";
import * as StringUtil from "./stringUtil.js";

const connection = mysql.createConnection(DBConfig);
const SELECT_TEMPLATE = "SELECT * FROM";
const INSERT_TEMPLATE = "INSERT INTO";

/**
 * Generates a SELECT SQL statement based on the provided table and where clause.
 *
 * @param {string} table - The name of the table to select from.
 * @param {string} where - The WHERE clause to filter the results.
 * @return {string} The generated SELECT SQL statement.
 */
const generateSelectSQL = (table, where) => {
    console.log(`${SELECT_TEMPLATE} ${table} ${where}`);
    return `${SELECT_TEMPLATE} ${table} ${where}`;
};

/**
 * Generates an SQL INSERT statement for a given table and object.
 *
 * @param {string} table - The name of the table to insert into.
 * @param {object} object - The object containing the values to insert.
 * @return {string} The generated SQL INSERT statement.
 */
const generateInsertSQL = (table, object) => {
    let sql = `${INSERT_TEMPLATE} ${table} (`;

    const entryArr = [...Object.entries(object)];
    entryArr.forEach(([key, _], idx, arr) => {
        const snakeCaseKey = StringUtil.camelCaseToSnakeCase(key);

        sql +=
            idx === arr.length - 1
                ? `${snakeCaseKey}) VALUES(`
                : `${snakeCaseKey}, `;
    });
    entryArr.forEach(([_, val], idx, arr) => {
        const curVal = typeof val === "number" ? val : `'${val}'`;

        sql += idx === arr.length - 1 ? `${curVal});` : `${curVal}, `;
    });

    console.log(sql);
    return sql;
};

export { connection, generateSelectSQL, generateInsertSQL };

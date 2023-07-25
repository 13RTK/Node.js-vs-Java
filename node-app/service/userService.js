import {
    connection,
    generateSelectSQL,
    generateInsertSQL,
} from "../utils/dbUtil.js";
import User from "../entity/user.js";

const errorAlert = "Error occur during SQL execution!!!";

/**
 * Inserts a user into the database.
 *
 * @param {Object} user - The user object to be inserted.
 * @return {number} The ID of the inserted user.
 */
const insertUser = async (user) => {
    try {
        const results = await dbExecutePromise(
            generateInsertSQL("tb_user", user)
        );

        console.log(`Insert result: ${results.insertId}`);
        return results.insertId;
    } catch (error) {
        console.error(`${errorAlert}(insert user)\n${error.message}\n`);
    } finally {
        connection.end();
    }
};

/**
 * Retrieves a user from the database based on their ID.
 *
 * @param {number} id - The ID of the user.
 * @return {Promise<User>} The user object.
 */
const queryUserById = async (id) => {
    const userId = id;

    try {
        const results = await dbExecutePromise(
            // `SELECT * FROM tb_user WHERE id = ${userId}`
            generateSelectSQL("tb_user", `WHERE id = ${userId}`)
        );

        if (results.length === 0) {
            throw new Error(`No such user of id : ${userId}`);
        }

        const {
            id,
            account_name: accountName,
            password,
            account_number: accountNumber,
        } = results[0];
        const user = new User(id, accountName, password, accountNumber);

        return user;
    } catch (error) {
        console.error(`${errorAlert}(query user)\n${error.message}\n`);
    }
};

/**
 * Updates a user by their ID.
 *
 * @param {string} id - The ID of the user.
 * @param {object} user - The updated user information.
 * @return {number} The ID of the updated user.
 */
const updateUserById = async (id, user) => {
    try {
        const oldUser = await queryUserById(id);
        if (!oldUser) {
            return;
        }

        const newAccountName = user.accountName || oldUser.accountName;
        const newPassword = user.password || oldUser.password;
        const newAccountNumber = user.accountNumber || oldUser.accountNumber;

        const results = await dbExecutePromise(
            `UPDATE tb_user SET account_name = "${newAccountName}", password = "${newPassword}",  account_number = "${newAccountNumber}" WHERE id = ${id}`
        );

        console.log(`Updated user of id ${id}`);

        return results.insertId;
    } catch (error) {
        console.error(`${errorAlert}(update user)\n${error.message}\n`);
    }
};

/**
 * Deletes a user by their ID.
 *
 * @param {number} id - The ID of the user to delete.
 * @return {number} The ID of the user that was deleted.
 */
const deleteUserById = async (id) => {
    try {
        const oldUser = await queryUserById(id);
        if (!oldUser) {
            return;
        }

        const results = await dbExecutePromise(
            `DELETE FROM tb_user WHERE id = ${id}`
        );

        console.log(`Deleted user of id ${id}`);

        return results.insertId;
    } catch (error) {
        console.error(`${errorAlert}(delete user)\n${error.message}\n`);
    }
};

/**
 * Executes a database query using a promise.
 *
 * @param {string} sql - The SQL query to execute.
 * @return {Promise} A promise that resolves to the query results.
 */
const dbExecutePromise = (sql) => {
    return new Promise((resolve, reject) => {
        connection.query(sql, (error, results) => {
            if (error) {
                reject(error);
            }

            resolve(results);
        });
    });
};

export { insertUser, queryUserById, updateUserById, deleteUserById };

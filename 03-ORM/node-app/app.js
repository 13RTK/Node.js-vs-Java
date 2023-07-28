import { Sequelize, DataTypes } from "sequelize";
import { mysqlConfig } from "./dbConfig.js";

const sequelize = new Sequelize(
    mysqlConfig.database,
    mysqlConfig.user,
    mysqlConfig.password,
    {
        host: mysqlConfig.host,
        dialect: mysqlConfig.dialect,
    }
);

const User = sequelize.define(
    "User",
    {
        id: {
            type: DataTypes.INTEGER,
            allowNull: false,
            primaryKey: true,
        },
        accountName: {
            type: DataTypes.STRING,
            field: "account_name",
        },
        accountNumber: {
            type: DataTypes.STRING,
            field: "account_number",
        },
        password: {
            type: DataTypes.STRING,
            field: "account_name",
        },
    },
    {
        tableName: "tb_user",
        createdAt: false,
        updatedAt: false,
    }
);

const userList = await User.findAll();
userList.forEach((user) => console.log(JSON.stringify(user)));

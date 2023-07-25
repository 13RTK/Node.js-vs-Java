import * as UserService from "../service/userService.js";

await UserService.insertUser({
    accountName: "John",
    password: "abcdef",
    accountNumber: "wafahl@gmail.com",
});
// console.log("demo");

// await UserService.queryUserById(1);
// await UserService.updateUserById(20, {
//     accountName: "John117",
// });

// await UserService.deleteUserById(6);

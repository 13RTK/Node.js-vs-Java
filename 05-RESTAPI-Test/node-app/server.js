import dotenv from "dotenv";
import app from "./app.js";

dotenv.config({ path: "./config.env" });
const port = process.env.port;

app.listen(port, () => {
    console.log(`Listening : ${port}`);
});

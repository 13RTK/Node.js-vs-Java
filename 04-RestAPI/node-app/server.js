import dotenv from "dotenv";
import express, { Router } from "express";
import cors from "cors";

dotenv.config({ path: "./config.env" });
const port = process.env.SERVER_PORT;

const corsOptions = {
    origin: "http://127.0.0.1:5500",
    optionsSuccessStatus: 200,
};

const app = express();
app.use(cors(corsOptions));
app.use(express.json());

app.post("/", (req, res) => {
    const body = req.body;
    console.log("Hello from post");

    res.status(200).json(body);
});

const router = express.Router();
router.route("/").get().post();

app.get("/:password?", (req, res) => {
    const password = req.params.password;
    console.log(password);

    res.status(200).json(password);
});

app.listen(port, () => {
    console.log(`Listening port : ${port}`);
});

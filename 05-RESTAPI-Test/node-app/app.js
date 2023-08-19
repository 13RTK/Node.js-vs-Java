import express from "express";
import router from "./routes/userRoute.js";
import morgan from "morgan";

const app = express();
app.use(express.json());
app.use(morgan("dev"));

app.use("/user", router);

export default app;

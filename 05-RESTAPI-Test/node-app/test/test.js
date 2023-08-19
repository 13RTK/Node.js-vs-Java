import { expect } from "expect";
import request from "supertest";
import app from "../app.js";

const response = await request(app).get("/user/23");

expect(response.status).toBe(200);
expect(response.body.data).toBe("User: 23");

import { expect } from "expect";
import request from "supertest";
import app from "../app.js";
import assert from "assert";

const response = await request(app).get("/user/23");

assert.strictEqual(response.status, 200);
assert.strictEqual(response.body.data, "User: 23");
expect(response.status).toBe(200);
expect(response.body.data).toBe("User: 23");

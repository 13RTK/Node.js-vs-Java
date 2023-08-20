import pino from "pino";
const transport = pino.transport({
    targets: [
        {
            target: "pino/file",
            options: { destination: "./log/pino/pino.log" },
            level: "info",
        },
        {
            target: "pino/file",
            options: { destination: "./log/pino/error.log" },
            level: "error",
        },
    ],
});

export const pinoLogger = pino(transport);

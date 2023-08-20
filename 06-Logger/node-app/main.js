import { logger as winstonLogger } from "./winstonLogger.js";
import { pinoLogger } from "./pinoLogger.js";

const winstonDemo = () => {
    winstonLogger.info("Log with winston");
    winstonLogger.error("Log with winston");
};

// winstonDemo();

const pinoDemo = () => {
    pinoLogger.info("Info in pino");
    pinoLogger.error("Error in pino");
};

pinoDemo();

/**
 * Converts a given string from camelCase to snake_case.
 *
 * @param {string} str - The string to be converted.
 * @return {string} The converted string in snake_case.
 */
const camelCaseToSnakeCase = (str) => {
    return str.replace(/([a-z])([A-Z])/g, "$1_$2").toLowerCase();
};

export { camelCaseToSnakeCase };

export default class {
    #id;
    #accountName;
    #password;
    #accountNumber;

    constructor(id, accountName, password, accountNumber) {
        this.#id = id;
        this.#accountName = accountName;
        this.#password = password;
        this.#accountNumber = accountNumber;
    }

    get getId() {
        return this.#id;
    }

    get getAccountName() {
        return this.#accountName;
    }

    get getPassword() {
        return this.#password;
    }

    get getAccountNumber() {
        return this.#accountNumber;
    }
}

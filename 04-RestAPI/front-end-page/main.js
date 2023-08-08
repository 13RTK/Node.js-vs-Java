const eleName = document.querySelector(".name");
const elePassword = document.querySelector(".password");
const eleNumber = document.querySelector(".number");
const btnSubmit = document.querySelector(".submit-btn");

const port = 8080;
const method = "POST";

const submitUser = async (event) => {
    event.preventDefault();

    try {
        const result = await fetch(`http://127.0.0.1:${port}`, {
            method,
            headers: { "content-type": "application/json" },
            body: JSON.stringify({
                accountName: eleName.value,
                password: elePassword.value,
                accountNumber: eleNumber.value,
            }),
        }).then((data) => data.json());

        alert(`Success send data: ${JSON.stringify(result)}`);
    } catch (error) {
        alert(`Error send, error message: ${error}`);
    } finally {
        // eleName.value = elePassword.value = eleNumber.value = "";
    }
};

btnSubmit.addEventListener("click", submitUser);

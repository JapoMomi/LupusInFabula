function showPasswordHint(toShow){
    const passwordComplex = document.getElementById("passwordComplex");
    if (toShow)
        passwordComplex.classList.remove("d-none");
    else
        passwordComplex.classList.add("d-none");
}

const criteria = {
    passwordLength: /^.{8,20}$/,
    passwordUppercase: /[A-Z]/,
    passwordLowercase: /[a-z]/,
    passwordNumber: /\d/,
    passwordSymbol: /[!@#$%^&*]/
};

function passwordComplexHint(password) {

    for (let criterion in criteria) {
        const element = document.getElementById(criterion);
        if (criteria[criterion].test(password)) {
            element.classList.remove('invalid');
            element.classList.add('valid');
        } else {
            element.classList.remove('valid');
            element.classList.add('invalid');
        }
    }
}

function showPassword(idInput) {
    const input = document.getElementById(idInput)
    const eyeIcon = document.getElementById(idInput + "_eyeIcon");

    if (eyeIcon.classList.contains("fa-eye")) {
        input.type = "text";
        eyeIcon.classList.remove("fa-eye");
        eyeIcon.classList.add("fa-eye-slash");
    } else {
        input.type = "password";
        eyeIcon.classList.remove("fa-eye-slash");
        eyeIcon.classList.add("fa-eye");
    }
}
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");
    form.addEventListener("submit", function(event) {
        const name = form.querySelector("input[name='name']").value;
        const price = form.querySelector("input[name='price']").value;

        if (!name || name.trim() === "") {
            alert("Name is required.");
            event.preventDefault();
        }

        if (!price || isNaN(price) || price <= 0) {
            alert("Valid price is required.");
            event.preventDefault();
        }
    });
});

function validateLoginForm() {
    const form = document.querySelector("form");
    const username = form.querySelector("input[name='username']").value;
    const password = form.querySelector("input[name='password']").value;

    if (username.trim() === "") {
        alert("Username is required.");
        return false;
    }

    if (password.trim() === "") {
        alert("Password is required.");
        return false;
    }

    return true;
}

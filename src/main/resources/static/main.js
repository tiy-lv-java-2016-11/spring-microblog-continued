
function validate() {
    document.getElementById("delete").addEventListener("click", function (e) {
        if (!confirm("Are you sure?")){
            e.preventDefault();}
    })
}

window.onload = validate;
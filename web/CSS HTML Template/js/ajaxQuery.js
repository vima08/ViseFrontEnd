
$(document).ready(function () {
    $("#submitData").submit(function () {
        var a = $("#formData").serializeObject();
        console.log(a);
        alert(a);
    })
});
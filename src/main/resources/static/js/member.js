
$("#join-btn").click(() => {
    let data = {
        memberId: $("#memberId").val(),
        password: $("#password").val(),
        nickname: $("#nickname").val()
    }

    $.ajax({
        type: "POST",
        url: "/member/join-proc",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: "json"
    }).done(function(res){
        alert(res.data);
        location.href = "/";
    }).fail(function(error){
        alert(error.responseJSON.message);
    });
});
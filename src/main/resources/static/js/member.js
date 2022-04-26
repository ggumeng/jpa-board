
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
        alert("가입이 완료되었습니다.");
        location.href = "/";
    }).fail(function(xhr, textStatus, errorThrown){
        console.log(xhr);
        console.log(textStatus);
        console.log(errorThrown);
        alert("회원가입에 실패하였습니다.");
    });
});
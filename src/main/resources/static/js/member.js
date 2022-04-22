
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
    }).fail(function(error){
        alert("이미 존재하는 회원입니다.");
    });
});
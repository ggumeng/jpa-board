
// 게시글 등록
$("#btn-save").click(() => {

    // 입력한 데이터들을 하나로 묶음
    let data = {
        title: $('#title').val(),
        content: $('#content').val()
    }

    // ajax 통신으로 데이터를 POST 방식으로 보냄
    // 성공할 시 '게시글 작성이 완료되었습니다' 라는 alert창과 함께 메인페이지로 이동
    $.ajax({
        type: "POST",
        url: "/board/write-proc",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: "json"
    }).done(function(res){
        alert("게시글 작성이 완료되었습니다.");
        location.href = "/";
    }).fail(function(error){
        alert("시스템 오류입니다.");
    });
});

$("#modify-form-btn").click(() => {
    let data = $("#idx").val();
    location.href = "/board/modify-form/" + data;
});

$("#modify-btn").click(() => {

    // 게시글 번호 변수 선언
    let data = {
        title: $("#title").val(),
        content: $("#content").val()
    };

    let idx = $("#idx").val();

    console.log(data);

    // ajax 통신으로 PUT 요청
    // 성공할 시 '게시글 작성이 완료되었습니다' 라는 alert창과 함께 메인페이지로 이동
    $.ajax({
        type: "PUT",
        url: "/board/" + idx,
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: "json"
    }).done(function(res){
        alert("성공");
        location.href = "/";
    }).fail(function(error){
        alert("오류");
    });
});

$("#delete-btn").click(() => {

    // 입력한 데이터들을 하나로 묶음
    let data = $("#idx").val();
    console.info(data);

    // ajax 통신으로 DELETE 요청
    // 성공할 시 '게시글 삭제가 완료되었습니다' 라는 alert창과 함께 메인페이지로 이동
    $.ajax({
        type: "DELETE",
        url: "/board/" + data
    }).done(function(res){
        alert("게시글 삭제가 완료되었습니다.");
        location.href = "/";
    }).fail(function(error){
        alert("오류");
    });
});


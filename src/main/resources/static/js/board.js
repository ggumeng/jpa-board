// 게시글 등록
$("#btn-save").click(() => {

    // 등록할 게시글의 제목과 내용을 하나로 묶음
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
        alert(res.data);
        location.href = "/";
    }).fail(function(error){
        alert(error.responseJSON.message);
    });
});

// 게시글 수정 폼 이동
$("#modify-form-btn").click(() => {
    let data = $("#idx").val();
    location.href = "/board/modify-form/" + data;
});

// 게시글 수정
$("#modify-btn").click(() => {

    // 수정할 제목과 내용을 하나로 묶음
    let data = {
        title: $("#title").val(),
        content: $("#content").val()
    };

    let idx = $("#idx").val();

    console.log(data);

    // ajax 통신으로 PUT 요청
    // 성공할 시 '게시글 수정이 완료되었습니다' 라는 alert창과 함께 메인페이지로 이동
    $.ajax({
        type: "PUT",
        url: "/board/" + idx,
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

// 게시글 삭제
$("#delete-btn").click(() => {

    // 게시글 번호 선언
    let data = $("#idx").val();
    console.info(data);

    // ajax 통신으로 DELETE 요청
    // 성공할 시 '게시글 삭제가 완료되었습니다' 라는 alert창과 함께 메인페이지로 이동
    $.ajax({
        type: "DELETE",
        url: "/board/" + data
    }).done(function(res){
        alert(res.data);
        location.href = "/";
    }).fail(function(error){
        alert(error.responseJSON.message);
    });
});

// 댓글 등록
$("#reply-btn").click(() => {

    // 게시판 번호와 댓글 내용을 하나로 묶음
    let data = {
        replyContent: $("#reply-content").val()
    }

    let boardIdx = $("#idx").val();

    // ajax 통신으로 POST 요청
    // 성공 시 "댓글 작성이 완료되었습니다." 라는 메세지와 함께 새로고침
    $.ajax({
        type: "POST",
        url: "/board/reply-proc/" + boardIdx,
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: "json"
    }).done(function(res){
        alert(res.data);
        location.reload();
    }).fail(function(error){
        alert(error.responseJSON.message);
    });
});

// 댓글 삭제
$("#reply-delete-btn").click(() => {

    // 댓글 번호 선언
    let replyIdx = $("#reply-idx").val();

    // ajax 통신으로 DELETE 요청
    // 성공할 시 '댓글 삭제가 완료되었습니다' 라는 alert창과 함께 메인페이지로 이동
    $.ajax({
        type: "DELETE",
        url: "/board/reply/" + replyIdx,
    }).done(function(res){
        alert(res.data);     
        location.reload();
    }).fail(function(error){
        alert(error.responseJSON.message);
    });
});


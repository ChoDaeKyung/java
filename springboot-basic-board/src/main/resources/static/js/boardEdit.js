$(document).ready(() => {
    checkSession(); // 세션 확인
    loadBoardEdit(); // 게시글 데이터 불러오기

    // 수정 완료 버튼 클릭 이벤트
    $('#updateBtn').click(function() {
        const title = $('#titleInput').val();
        const content = $('#contentInput').val();
        const boardId = $('#hiddenId').val(); // boardId 가져오기

        // 수정된 내용을 서버에 전송
        $.ajax({
            type: 'PUT',
            url: `/api/board/${boardId}`,
            contentType: 'application/json',
            data: JSON.stringify({
                id: boardId, // id 추가
                title: title,
                content: content
            }),
            success: function(response) {
                alert('게시글이 수정되었습니다.');
                location.href = `/`; // 수정 후 게시판 리스트 페이지로 이동
            },
            error: function(error) {
                console.error('수정 오류:', error);
                alert('게시글 수정에 실패했습니다.');
            }
        });
    });
});

// 세션 체크 함수
let checkSession = () => {
    let hUserId = $('#hiddenUserId').val();

    if (hUserId == null || hUserId === '') {
        window.location.href = "/member/login"; // 로그인 페이지로 이동
    }
}

// 게시글 데이터 로드 함수
let loadBoardEdit = () => {
    let hId = $('#hiddenId').val();

    $.ajax({
        type: 'GET',
        url: '/api/board/' + hId,
        success: (response) => {
            $('#titleInput').val(response.title);
            $('#contentInput').val(response.content);
        },
        error: function (error) {
            console.error('오류 발생:', error);
            alert('게시글 데이터를 불러오는데 오류가 발생했습니다.');
        }
    });
}

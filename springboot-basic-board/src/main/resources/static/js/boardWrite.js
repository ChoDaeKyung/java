$(document).ready(() => {
    $('#write').click((e) => {
        e.preventDefault(); // 기본 제출 방지
        const title = $('#title').val();
        const content = $('#content').val();
        const userId = $('#hiddenUserId').val(); // 사용자 ID 가져오기

        const boardWriteRequestDTO = {
            title: title,
            content: content,
            userId: userId // userId를 DTO에 포함
        };

        $.ajax({
            type: 'POST',
            url: '/api/board/write', // 게시글 작성 API URL
            contentType: 'application/json',
            data: JSON.stringify(boardWriteRequestDTO),
            success: (response) => {
                alert('게시글이 작성되었습니다.');
                window.location.href = response.url; // 성공 시 리디렉션
            },
            error: (error) => {
                console.error('오류 발생:', error);
                alert('게시글 작성 중 오류가 발생했습니다.');
            }
        });
    });
});

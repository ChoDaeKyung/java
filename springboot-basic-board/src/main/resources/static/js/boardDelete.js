$(document).ready(function() {
    $('#deleteBtn').click(function() {
        const boardId = $('#hiddenId').val();
        if (confirm('게시글을 삭제하시겠습니까?')) {
            $.ajax({
                type: 'DELETE',
                url: `/api/board/${boardId}`, // Adjust the URL to match your API endpoint
                success: function() {
                    alert('게시글이 삭제되었습니다.');
                    location.href = '/'; // Redirect after successful deletion
                },
                error: function() {
                    alert('게시글 삭제에 실패하였습니다. 다시 시도해주세요.');
                }
            });
        }
    });
});
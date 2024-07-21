$(document).ready(function() {

    $("#addBtn").click(function() {
        console.log("추가 버튼 클릭됨");
        $("#addModal").css("display", "block");
    });


    $(".close").click(function() {
        $("#addModal").css("display", "none");
    });


    $(window).click(function(event) {
        if (event.target == $("#addModal")[0]) {
            $("#addModal").css("display", "none");
        }

        if (event.target == $("#editModal")[0]) {
            $("#editModal").css("display", "none");
        }
    });


    $(".studentName").click(function() {
        let row = $(this).closest("tr");
        let id = row.find(".studentId").text();
        let name = row.find(".studentName").text();
        let address = row.find(".studentAddress").text();
        let school = row.find(".studentSchool").text();
        let major = row.find(".studentMajor").text();

        $("#editId").val(id);
        $("#editName").val(name);
        $("#editAddress").val(address);
        $("#editSchool").val(school);
        $("#editMajor").val(major);
        $("#editModal").css("display", "block");
    });


    $("#editModal .close").click(function() {
        $("#editModal").css("display", "none");
    });


    $(".deleteBtn").click(function() {
        console.log("삭제 버튼 클릭")
        if (confirm("정말 삭제하시겠습니까?")) {
            let row = $(this).closest("tr");
            let studentId = row.find(".studentId").text();
            $.ajax({
                url: '/students/delete',
                type: 'POST',
                data: { id: studentId },
                success: function(response) {
                    if (response === true) {
                        alert("삭제되었습니다.");
                        row.remove();
                    } else {
                        alert("삭제에 실패했습니다.");
                    }
                },
                error: function(error) {
                    alert("삭제 중 오류가 발생했습니다.");
                    console.error("Error:", error);
                }
            });
        }
    });

});
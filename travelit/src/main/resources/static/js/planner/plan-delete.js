$("#deleteBtn").click(function () {
    $.ajax({
        type: "DELETE",
        url: "/planner/plan-delete",  // replace with your actual endpoint
        contentType: "application/json",
        data: JSON.stringify(parseInt($("#planId").val())),
        success: function (response) {
            console.log(response);
            window.location.href = "/planner/main";
        },
        error: function (error) {
            console.error("Error sending data:", error);
        }
    });
});
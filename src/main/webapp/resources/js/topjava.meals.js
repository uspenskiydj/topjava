var ctx;

$(function () {
    ctx = {
        ajaxUrl: "ajax/profile/meals/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        }),
        updateTable: function () {
            let startDate = $('#startDate').val();
            let endDate = $('#endDate').val();
            let startTime = $('#startTime').val();
            let endTime = $('#endTime').val();

            $.get(ctx.ajaxUrl + "filter?startDate=" + startDate + "&endDate="
                + endDate+ "&startTime=" + startTime + "&endTime=" + endTime, function (data) {
                ctx.datatableApi.clear().rows.add(data).draw();
            });
        }
    };
    makeEditable();
});

function clearFilter() {
    $('#filter').find(":input").val("");
    updateTable();
}
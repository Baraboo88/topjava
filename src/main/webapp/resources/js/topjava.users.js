// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/admin/users/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
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
            })
        }
    );
});


function handleChange(checkbox, id) {

    if(checkbox.checked == true){
        $.ajax({
            url: context.ajaxUrl+ 'enable?id=' + id + '&enableStatus=true',
            type: "GET"
        }).done(function () {
            updateTable();
        });
    }
    else {
        $.ajax({
            url: context.ajaxUrl+ 'enable?id=' + id + '&enableStatus=false',
            type: "GET"
        }).done(function () {
            updateTable();

        });
    }
}

$(function() {
    $('td:first-child input').change(function() {
        $(this).closest('tr').toggleClass("highlight", this.checked);
    });
});




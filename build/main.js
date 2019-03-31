$(function () {

    function getAll() {
        let customerContainer = $('#customers_container');
        customerContainer.empty().hide();
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/SpringRESTExample_war/api/v1/customers',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data, status, xhr) {
                console.log(data);
                // ne pabotaet :(
            },
            error: function (result, status, xhr) {
                // a tyt vsio pabotaet
                JSON.parse(result.responseText).forEach(function (item) {
                    customerContainer.append('<tr>\n' +
                        '      <th scope="row">' + item['id'] + '</th>\n' +
                        '      <td>' + item['lastName'] + '</td>\n' +
                        '      <td>' + item['email'] + '</td>\n' +
                        '    </tr>')
                });
            }
        });
        customerContainer.hide().slideDown()
    }

    var searchResult = $('#searchResult');

    $('#performSearch').click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        e.stopImmediatePropagation();

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/SpringRESTExample_war/api/v1/customers/' + $('#inputId').val(),
            dataType: 'json',
            contentType: 'application/json',
            success: function (data, status, xhr) {
                // ne pabotaet :(
            },
            error: function (result, status, xhr) {
                // a tyt vsio pabotaet
                if (result.responseText === '') {
                    searchResult.empty().append('<strong>NOT FOUND</strong>');
                    return;
                }

                let data = JSON.parse(result.responseText);
                if (data['lastName'] === undefined || data['email'] === undefined) {
                    searchResult.empty().append('<strong>NOT FOUND</strong>');
                    return;
                }
                let res = '<form class="form-inline">\n' +
                    '  <div class="form-group mx-sm-3 mb-2">\n' +
                    '    <label for="editInputName" class="sr-only">Name</label>\n' +
                    '    <input required type="text" class="form-control" id="editInputName" placeholder="Name" value="' + data['lastName'] + '">\n' +
                    '  </div>\n' +
                    '  <div class="form-group mx-sm-3 mb-2">\n' +
                    '    <label for="editInputEmail" class="sr-only">Email</label>\n' +
                    '    <input required type="email" class="form-control" id="editInputEmail" placeholder="Email" value="' + data['email'] + '">\n' +
                    '  </div>\n' +
                    '  <button id="performEdit" class="btn btn-primary mx-sm-3 mb-2">Edit</button>\n' +
                    '  <button id="performDelete" class="btn btn-danger mx-sm-3 mb-2">Delete</button>\n' +
                    '</form>';
                searchResult.empty().append(res);

                $('#performEdit').click(function (e) {
                    e.preventDefault();
                    e.stopPropagation();
                    e.stopImmediatePropagation();
                    let sendData = {'lastName': $('#editInputName').val(), 'email': $('#editInputEmail').val()};
                    $.ajax({
                        type: 'PUT',
                        url: 'http://localhost:8080/SpringRESTExample_war/api/v1/customers/' + data['id'],
                        dataType: 'json',
                        contentType: 'application/json',
                        data: JSON.stringify(sendData),
                        success: function (data, status, xhr) {
                            // ne pabotaet :(
                            $('#inputId').val(data['id']);
                            $('#performSearch').click();
                            getAll()

                        },
                        error: function (result, status, xhr) {
                            // a tyt vsio pabotaet

                        }
                    });

                    return false;
                });

                $('#performDelete').click(function () {
                    $.ajax({
                        type: 'DELETE',
                        url: 'http://localhost:8080/SpringRESTExample_war/api/v1/customers/' + data['id'],
                        dataType: 'json',
                        contentType: 'application/json',
                        success: function () {
                            console.log('success')
                        },
                        error: function () {
                            console.log('tozhe success')
                        }
                    })
                })

            }
        });
        return false;
    });

    $('#performAdd').click(function (e) {

        e.preventDefault();
        e.stopPropagation();
        e.stopImmediatePropagation();

        let sendData = {'lastName': $('#addInputName').val(), 'email': $('#addInputEmail').val()};
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/SpringRESTExample_war/api/v1/customers/',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(sendData),
            success: function () {
                console.log('success');
            },
            error: function () {
                getAll()
            }
        });

        return false;
    });

    getAll()
});
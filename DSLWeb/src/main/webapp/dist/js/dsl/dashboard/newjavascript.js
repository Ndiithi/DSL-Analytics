$(document).ready(function () {
    $.ajax({
        type: 'GET', // define the type of HTTP verb we want to use
        url: '/get_pub_data/', // the url from server we that we want to use
        contentType: 'application/json; charset=utf-8',
        dataType: 'json', // what type of data do we expect back from the server
        encode: true,
        success: function (data, textStatus, jqXHR) {
        console.log(data);
            displayHivStatus(data);
            displayArtStatus(data);
        },
        error: function (response, request) {
            //    console.log("got an error fetching wards");
            var parsed_data = JSON.parse(response.responseText);
        }

    });
});

function displayHivStatus(data){
    $.each(data, function (index, objValue) {
       //var ward=new Ward(objValue.name);
       var elementId="hiv_status_chart";
       var categoriee= ['HIV+', 'HIV-', 'HIV Status Not Known']
       var titlee = 'HIV STATUS';
       var serie = [{
                        name: 'Female',
                        data: [objValue.hiv_positive_f, objValue.HIV_negative_f, objValue.HIV_unknown_status_f]
                    }, {
                        name: 'Male',
                        data: [objValue.hiv_positive_m, objValue.HIV_negative_m, objValue.HIV_unknown_status_m]
                    }];
       stackedBar(elementId,titlee,categoriee,serie);
    });
}


function displayArtStatus(data){
    $.each(data, function (index, objValue) {
       //var ward=new Ward(objValue.name);
       var elementId="art_status_chart";
       var categoriee= ['HIV+', 'HIV+ on ARV', 'HIV+ Not on ARV']
       var titlee = 'ART STATUS';
       var serie = [{
                        name: 'Female',
                        data: [objValue.hiv_positive_f, objValue.HIV_positive_on_arv_f, objValue.HIV_positive_not_on_arv_f]
                    }, {
                        name: 'Male',
                        data: [objValue.hiv_positive_m, objValue.HIV_positive_on_arv_m, objValue.HIV_positive_not_on_arv_m]
                    }];
       stackedBar(elementId,titlee,categoriee,serie);
    });
}



$( ".nav .dropdown" ).click(function() {
  alert( "Handler for .click() called." );
});
$(document).ready(function () {
    $('#submitbutton').click(function() {
        var a = serializeForm();
        callPercentage();
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/ViseFrontEnd_war_exploded/resource/experiment/run',
            data: $('#formx').serialize(),
            success: function (data) {
                drawResult(data);
                reRun();
                $.get('http://localhost:8080/ViseFrontEnd_war_exploded/resource/database/generate',
                    function onAjaxSuccess(objId) {
                            $(".rerunbtn").filter(":last").val(objId);
                            $.post('http://localhost:8080/ViseFrontEnd_war_exploded/resource/database/saveForm',
                                {data: JSON.stringify(a), objId: objId});
                        });
            },
            error: function (xhr, str) {
                alert('Возникла ошибка: ' + xhr.responseCode);
            },
            dataType: "json"
        });
    });

    $("#formx").change(function() {
        $("#submitbutton").prop("disabled", false);
    });


    $("#buttons").on("click", ".rerunbtn", function () {
        $.post('http://localhost:8080/ViseFrontEnd_war_exploded/resource/database/getParam',
            {expId: $(this).val()},
            function onAjaxSuccess(data) {
                $("[attr_id]").each(function () {
                    $(this).val(data[$(this).attr("attr_id")]);
                });
                callPercentage();
                $.get('http://localhost:8080/ViseFrontEnd_war_exploded/resource/experiment/run',
                    $('#formx').serializeObject(),
                    function onGetSuccess(data) {
                        drawResult(data);
                    },
                    "json"
                );
            },
            "json"
        );
    });

    function reRun() {
        //    изменение кнопки
        $("#buttons").append("<button type='submit' id='rerunbutton' class='rerunbtn btn btn-primary tm-btn-search'>ReRun Experiment</button>");
        $("#submitbutton").prop("disabled", true);
    };


    function serializeForm() {
        var data = {};
        $("[attr_id]").each(function () {
            data[$(this).attr("attr_id")] = $(this).val()
        });
        return data;
    };

    function drawResult(data) {
        var variation = $('#var').val();
        var xData = (variation === "mu") ? data.mu : data.sigma;

        //console.log(data);
        var trace1 = {
            x: xData,
            y: data.capital,
            mode: 'lines+markers',
            name: 'Result',
            line: {shape: 'spline'},
            type: 'scatter'
        };
        var trace2 = {
            x: xData,
            y: data.peopleCount,
            mode: 'lines+markers',
            name: 'People Count',
            line: {shape: 'spline'},
            type: 'scatter'
        };
        var plotdata = [trace1, trace2];
        Plotly.newPlot('myDiv', plotdata);
        //$('#results').html(data);
    };

    function callPercentage() {
        var elem = document.getElementById("myBar");
        var id = setInterval(frame, 100);
        var reset = 0;
        function frame() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/ViseFrontEnd_war_exploded/resource/experiment/percentage',
                success: function(data) {
                    elem.style.width = data * 100 + '%';
                    if (data >= 1) reset++;
                    if (reset >= 5) {
                        clearInterval(id);
                        reset = 0;
                    }
                    //$('#results').html(data);
                },
                error:  function(xhr, str){
                    alert('Возникла ошибка: ' + xhr.responseCode);
                },
                dataType: "json"
            });
        }
    }
});

$(document).ready(function(){
    var $dateInputs = $('.input-daterange input');
    $dateInputs.datetimepicker({
        format: 'DD/MM/YYYY'
    });
    $dateInputs.eq(0).on("dp.change", function (e) {
        $dateInputs.eq(1).data("DateTimePicker").minDate(e.date);
    });
    $dateInputs.eq(1).on("dp.change", function (e) {
        $dateInputs.eq(0).data("DateTimePicker").maxDate(e.date);
    });

    $(".bs-slider input").slider({});
});
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

    var bs_slider_options = {};
    $(".bs-slider input").each(function() {
        if($(this).val()) {
            bs_slider_options = $.extend(bs_slider_options, {
                value: $(this).val().split(",").map(Number)
            });
        }
        $(this).slider(bs_slider_options);
    });
});

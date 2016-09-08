/**
 * Created by Langley on 9/4/16.
 */

currentCount = 3;
$('.oneMore').on('click', function() {
    var _this = $(this).parent(),
        target = _this.siblings().first(),
        clone = target.clone();

    console.log(clone.toString());
    search_condition = '<span class="field"> <select class="search-conditional" name="searchconditional_2" id="searchconditional_2"> <option value="AND">AND</option> <option value="OR">OR</option> <option value="NOT">NOT</option> </select> </span>';

    clone.prepend(search_condition);

    search_condition_name = "searchconditional_" + currentCount;
    query_name = "query_" + currentCount;
    searchin_name = "searchin_" + currentCount;
    clone.find(".search-conditional").attr("id", search_condition_name).attr("name", search_condition_name);
    clone.find("input").attr("id", query_name).attr("name", query_name).val("");
    clone.find(".search-in").attr("id", searchin_name).attr("name", searchin_name);
    clone.find('.delete').on('click', function () {
        console.log("start delete")
        if($(this).closest(".search-border-box").find(".search-info-box").length === 1) {
            $(this).parent().find("input").val("");
        } else {
            $(this).parent().remove();
            currentCount--;
        }
        return false;
    });
    currentCount++;
    _this.before(clone);
});

$(".delete").click(function () {
    if($(this).closest(".search-border-box").find(".search-info-box").length === 1) {
        $(this).parent().find("input").val("");
        console.log("min filter: " + currentCount)
    } else {
        $(this).parent().remove();
        currentCount--;
        console.log("Remove! current Count: " + currentCount)
    }
    return false;
});
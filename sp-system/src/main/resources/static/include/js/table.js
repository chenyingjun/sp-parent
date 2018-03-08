/**
 * @author chenyingjun
 */
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) { // author: meizz
    var o = {
        "M+": this.getMonth() + 1,
        // 月份
        "d+": this.getDate(),
        // 日
        "H+": this.getHours(),
        // 小时
        "m+": this.getMinutes(),
        // 分
        "s+": this.getSeconds(),
        // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3),
        // 季度
        "S": this.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$(function($) {
    $("form").each(function() {
        var form = $(this);

        $(form).find("select[name='pageSize']").bind("change",
        function() {
            $(form).submit();
        });

    });
});

// 定义分页对象
var table = function(requestUrl, formId, tableId, operHtml, useCache) {

    // 防止重复提交
    var hand = $("#" + tableId).attr("data-hand");

    if (hand == "1") {

        return;
    }

    $("#global-error").remove();

    $("#table_processing").remove();

    $("#" + tableId).before('<div id="table_processing" class="dataTables_processing" >正在加载数据...</div>');

    // 获取表格的td个数
    var tdSize = $("#" + tableId).find("thead > tr > th").size();

    $("#table_processing").show();

    var paramInput = $("#" + formId).find("input,select,textarea");

    var param = {};

    if (useCache) {

        if (window.localStorage) {

            if (localStorage[tableId]) {

                var localParam = jQuery.parseJSON(localStorage[tableId]);

                for (var name in localParam) {

                    $("#" + formId).find("input[name='" + name + "'],select[name='" + name + "'],textarea[name='" + name + "']").val(localParam[name]);

                    param[name] = localParam[name];
                }
            } else {

                $(paramInput).each(function() {

                    var name = $(this).attr("name");

                    var value = $(this).val();

                    var id = $(this).attr("id");

                    $("#" + id).parent().removeClass("has-error").find(".error").html("");

                    if (value != "") {

                        param[name] = value;
                    }
                });
            }
        } else {

            $(paramInput).each(function() {

                var name = $(this).attr("name");

                var value = $(this).val();

                var id = $(this).attr("id");

                $("#" + id).parent().removeClass("has-error").find(".error").html("");

                if (value != "") {

                    param[name] = value;
                }
            });
        }
    } else {

        $(paramInput).each(function() {

            var name = $(this).attr("name");

            var value = $(this).val();

            var id = $(this).attr("id");

            $("#" + id).parent().removeClass("has-error").find(".error").html("");

            if (value != "") {

                param[name] = value;
            }
        });
    }

    $.ajax({
        type: "post",
        url: requestUrl,
        dataType: "json",
        data: param,
        cache: false,
        complete: function() {

            $("#" + tableId).removeAttr("data-hand");

            $("#table_processing").hide();
        },
        success: function(resp) {

            var globalErrorHtml = '<div id="global-error" class="alert alert-danger" style="margin-bottom: 10px;">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<div>';

            if (resp) {

                var code = resp.code;

                if (code) {

                    if (code == "200") {

                        if (window.localStorage) {

                            localStorage[tableId] = JSON.stringify(param);
                        }

                        var page = resp.data;

                        var data = page.list;

                        if (data) {
                            var size = $(data).size();

                            if (size > 0) {

                                successFun(data, operHtml);

                            } else {

                                $("#" + tableId).find("tbody").remove();

                                var tbody = '<tbody><tr><td colspan="' + tdSize + '">列表信息为空，请重置查询条件</td></tr></tbody>';

                                $("#" + tableId).append(tbody);
                            }
                            
                        } else {

                            $("#" + tableId).find("tbody").remove();

                            var tbody = '<tbody><tr><td colspan="' + tdSize + '">列表信息为空，请重置查询条件</td></tr></tbody>';

                            $("#" + tableId).append(tbody);
                        }

                        if (page) {
                            // 获取总页数
                            var totalPageNo = page.pages;

                            // 当前页数
                            var pageNo = page.pageNum;

                            var tfoot = "";

                            if (page.pageNum >= 1) {

                                tfoot = '<div class="dataTables_info" id="table1_info">从 ' + ((page.pageNum - 1) * (page.pageSize) + 1) + ' 到 ' + ((page.pageNum - 1) * (page.pageSize) + page.size) + ' 条记录 -- 总记录数为 ' + (page.total) + ' 条</div><div class="dataTables_paginate paging_simple_numbers" id="table1_paginate">';
                            } else {

                                tfoot = '<tfoot><tr><th colspan="' + tdSize + '"><div class="dataTables_info" id="table1_info">总记录数为 ' + (page.total) + ' 条</div><div class="dataTables_paginate paging_simple_numbers" id="table1_paginate">';
                            }

                            if (totalPageNo > 1) {
                                if (page.hasPreviousPage) {
                                    tfoot = tfoot + '<a class="paginate_button previous" data-page-no=' + (page.pageNum - 1) + '>上一页</a>';
                                } else {
                                    tfoot = tfoot + '<a class="paginate_button previous disabled">上一页</a>';
                                }
                            }

                            if (totalPageNo <= 5) {
                                for (var i = 1; i <= totalPageNo; i++) {
                                    if (i == pageNo) {
                                        tfoot = tfoot + '<a class="paginate_button current">' + i + '</a>';
                                    } else {
                                        tfoot = tfoot + '<a class="paginate_button" + data-page-no="' + i + '">' + i + '</a>';
                                    }
                                }
                            } else {
                                if (pageNo == 1) {
                                    tfoot = tfoot + '<a class="paginate_button current">1</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="2">2</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="3">3</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="4">4</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + totalPageNo + '">' + totalPageNo + '</a>';
                                } else if (pageNo == 2) {
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="1">1</a>';
                                    tfoot = tfoot + '<a class="paginate_button current">2</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="3">3</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="4">4</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + totalPageNo + '">' + totalPageNo + '</a>';
                                } else if (pageNo >= 3 && pageNo < (totalPageNo - 1)) {
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="1">1</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + (pageNo - 1) + '">' + (pageNo - 1) + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button current">' + pageNo + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + (pageNo + 1) + '">' + (pageNo + 1) + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + totalPageNo + '">' + totalPageNo + '</a>';
                                } else if (pageNo == (totalPageNo - 1)) {
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="1">1</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + (pageNo - 2) + '">' + (pageNo - 2) + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + (pageNo - 1) + '">' + (pageNo - 1) + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button current">' + pageNo + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + totalPageNo + '">' + totalPageNo + '</a>';
                                } else if (pageNo == totalPageNo) {
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="1">1</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + (pageNo - 3) + '">' + (pageNo - 3) + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + (pageNo - 2) + '">' + (pageNo - 2) + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button" data-page-no="' + (pageNo - 1) + '">' + (pageNo - 1) + '</a>';
                                    tfoot = tfoot + '<a class="paginate_button current">' + pageNo + '</a>';
                                }
                            }

                            if (totalPageNo > 1) {
                                if (page.hasNextPage) {
                                    tfoot = tfoot + '<a class="paginate_button next" data-page-no=' + (page.pageNum + 1) + '>下一页</a>';
                                } else {
                                    tfoot = tfoot + '<a class="paginate_button next disabled">下一页</a>';
                                }
                            }
                        }

                        tfoot = tfoot + '</div>';

                        $("#" + tableId).parent().parent().find("#table1_info").remove();
                        $("#" + tableId).parent().parent().find("#table1_paginate").remove();

                        $("#" + tableId).parent().parent().append(tfoot);

                        $(".paginate_button").bind("click",
                        function() {

                            var pageNum = $(this).attr("data-page-no");

                            if (pageNum) {

                                $("#" + formId).find("input[name='pageNum']").val(pageNum);

                                $("#" + formId).submit();
                            }
                        });
                    } else {

                        var formValid = resp.t;

                        if (formValid) {

                            var globalErrors = formValid.globalErrors;

                            if (globalErrors.length > 0) {

                                globalErrors = JSON.stringify(globalErrors);

                                globalErrors = decodeURIComponent(globalErrors);

                                globalErrorHtml = globalErrorHtml + globalErrors + '</div></div>';

                                $("#" + tableId).parent().before(globalErrorHtml);
                            }

                            var fieldErrors = formValid.fieldErrors;

                            $("#" + formId).find("input").each(function() {

                                var id = $(this).attr("id");

                                var name = $(this).attr("name");

                                var value = $(this).val();

                                var type = $(this).attr("type");

                                $("#" + id).parent().removeClass("has-error").find(".error").html("");

                                var fieldError = fieldErrors[name];

                                if ("hidden" != type && fieldError) {

                                    fieldError = decodeURIComponent(fieldError);

                                    var parentClass = $("#" + id).parent().attr("class");

                                    if (parentClass == "input-group") {
                                        $("#" + id).parent().parent().addClass("has-error");

                                        var error = $("#" + id).parent().parent().find(".error");

                                        if (error.size() > 0) {

                                            $(error).html(fieldError).css("display", "block");
                                        } else {

                                            $("#" + id).parent().after('<label class="error" for="' + id + '" style="display:block">' + fieldError + '</label>');
                                        }
                                    } else {

                                        $("#" + id).parent().addClass("has-error");

                                        var error = $("#" + id).parent().find(".error");

                                        if (error.size() > 0) {

                                            $(error).html(fieldError).css("display", "block");
                                        } else {

                                            $("#" + id).after('<label class="error" for="' + id + '" style="display:block">' + fieldError + '</label>');
                                        }
                                    }
                                }
                            });

                            $("#" + formId).find("select").each(function() {

                                var id = $(this).attr("id");

                                var name = $(this).attr("name");

                                var value = $(this).val();

                                var type = $(this).attr("type");

                                $("#" + id).parent().removeClass("has-error").find(".error").html("");

                                $("#s2id_" + id).find("a").removeAttr("style");

                                var fieldError = fieldErrors[name];

                                if ("hidden" != type && fieldError) {

                                    fieldError = decodeURIComponent(fieldError);

                                    var parentClass = $("#" + id).parent().attr("class");

                                    if (parentClass == "input-group") {
                                        $("#" + id).parent().parent().addClass("has-error");

                                        var error = $("#" + id).parent().parent().find(".error");

                                        if (error.size() > 0) {

                                            $(error).html(fieldError).css("display", "block");
                                        } else {

                                            $("#" + id).parent().after('<label class="error" for="' + id + '" style="display:block">' + fieldError + '</label>');
                                        }
                                    } else {

                                        $("#" + id).parent().addClass("has-error");

                                        var error = $("#" + id).parent().find(".error");

                                        if (error.size() > 0) {

                                            $(error).html(fieldError).css("display", "block");
                                        } else {

                                            $("#" + id).after('<label class="error" for="' + id + '" style="display:block">' + fieldError + '</label>');
                                        }
                                    }

                                    $("#s2id_" + id).find("a").attr("style", "border-color:#a94442");
                                }
                            });
                        } else {

                            globalErrorHtml = globalErrorHtml + '服务器繁忙</div></div>';

                            $("#" + tableId).parent().before(globalErrorHtml);
                        }
                        return;
                    }

                } else {

                    globalErrorHtml = globalErrorHtml + '服务器繁忙</div></div>';

                    $("#" + tableId).parent().before(globalErrorHtml);
                }
            } else {

                globalErrorHtml = globalErrorHtml + '服务器繁忙</div></div>';

                $("#" + tableId).parent().before(globalErrorHtml);
            }
        },
        error: function(resp) {

            var tfoot = '<tfoot><tr><th colspan="' + tdSize + '">加载失败，请重置查询条</th></tr></tfoot>';

            $("#" + tableId).find("tfoot").remove();

            $("#" + tableId).append(tfoot);
        }
    });
};
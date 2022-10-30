var Utils = {
	isEmpty: function(obj) {
		return (typeof obj === "undefined" || obj === "null" || obj === "" || (typeof obj === "string" && obj.trim() === "") || obj === null || obj.length === 0);
	},
	NVL: function(o, t) {
		if(Utils.isEmpty(o)) return t;
		return o;
	},
	objToPrams: function(obj) {
			return Object.keys(obj).map(_key=>_key+"="+obj[_key]).join("&");
	},
	dateFormat: function(date, mod) {
		date = new Date(date);
		
		var year = date.getFullYear();
		var month = (1 + date.getMonth());
		month = month >= 10 ? month : '0' + month;
		var day = date.getDate();
		day = day >= 10 ? day : '0' + day;
		var hours = date.getHours();
		hours = hours >= 10 ? hours : '0' + hours;
		var minutes = date.getMinutes();
		minutes = minutes >= 10 ? minutes : '0' + minutes;
		var seconds = date.getSeconds();
		seconds = seconds >= 10 ? seconds : '0' + seconds;
		
		mod = String(mod).toUpperCase();
		if(mod == "YY" || mod == "YYYY") {
			return  year;
		} else if(mod == "YYMM" || mod == "YYYYMM") {
			return  year + '-' + month;
		} else if(mod == "YYMMDD" || mod == "YYYYMMDD") {
			return  year + '-' + month + '-' + day;
		} else if(mod == "YYMMDDHHMM" || mod == "YYYYMMDDHHMM") {
			return  year + '-' + month + '-' + day + " " + hours + ":" + minutes;
		} else if(mod == "YYMMDDHHMMSS" || mod == "YYYYMMDDHHMMSS") {
			return  year + '-' + month + '-' + day + " " + hours + ":" + minutes + ":" + seconds;
		} else if(mod == "DD") {
			return day;
		}
		return  year + '-' + month + '-' + day;
	},
	
	openWindowWithGet: function(url, option, data) {
		if(!Utils.isEmpty(option["width"])) {
			option["left"] = Utils.NVL(option["left"], (window.screenX || window.screenLeft || 0) + (document.body.clientWidth / 2) - (option["width"] / 2));
		}
		if(!Utils.isEmpty(option["height"])) {
			option["top"] = Utils.NVL(option["top"], (window.screenY || window.screenTop || 0) + (document.body.clientHeight / 2) - (option["height"] / 2));
		}
		
		let target = url.split("/").slice(-1);
		window.open(url + "?" + Utils.objToPrams(data), target, Object.entries(option).map(row=>row.join("=")).join(", "));
	},
	
	openWindowWithPost: function(url, option, data) {
		let form = document.createElement("form");
		
		if(!Utils.isEmpty(option["width"])) {
			option["left"] = Utils.NVL(option["left"], (window.screenX || window.screenLeft || 0) + (document.body.clientWidth / 2) - (option["width"] / 2));
		}
		if(!Utils.isEmpty(option["height"])) {
			option["top"] = Utils.NVL(option["top"], (window.screenY || window.screenTop || 0) + (document.body.clientHeight / 2) - (option["height"] / 2));
		}
		
		let target = url.split("/").slice(-1);
		window.open("", target, Object.entries(option).map(row=>row.join("=")).join(", "));
		form.target = target;
		form.method = "POST";
		form.action = url;
		form.style.display = "none";
		
		for (var key in data) {
			var input = document.createElement("input");
			input.type = "hidden";
			input.name = key;
			input.value = data[key];
			form.appendChild(input);
		}
		
		document.body.appendChild(form);
		form.submit();
		document.body.removeChild(form);
	},
	comma: (n)=>{
		let [num, decimal] = n.toString().split("."); 
		return num.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (TUI_UTILS.isEmpty(decimal) ? "" : ("." + decimal));
	},
	formatD: (v, f)=>parseFloat(Math.round(v * 100) / 100).toFixed(f),
	
	
	
    /**
     * message 함수 추가
     */
    message: function(msgNo, params) {
        var oMsg = messageAlt[msgNo.toUpperCase()];
        if (!Utils.isEmpty(params)) {
            for (var k in params) {
                oMsg = oMsg.split(k).join(params[k]);
            }
        }
        return oMsg;
    },
    /**
     * 코드 여러개 가져올시 그룹에 맞게 리턴 해주기 위한 함수
     */
    getCode: function(codeList, grupCd) {
        var retuList = new Array();
        for (var i = 0; i < codeList.length; i++) {
            var map = new Object();
            if (codeList[i].grupCd == grupCd) {
                map['value'] = codeList[i].itemCd;
                map['text'] = codeList[i].itemNm;
                retuList.push(map);
            }
        }
        return retuList;
    },
    /**
     * Return Message 치환 함수
     */
    returnMessage: function(messageParams) {
        var retuList = new Object();
        if (messageParams.retnCd == 'OK') {
            retuList = messageParams;
        } else {
            var indexStr = messageParams.retnMsg.indexOf("☞") + 1;
            var indexEnd = messageParams.retnMsg.indexOf("☜");
            retuList["retnCd"] = "ERR";
            retuList["retnMsg"] = messageParams.retnMsg.substring(indexStr, indexEnd);
        }
        return retuList;
    },
    /**
     * input 값 셋팅
     */
    inputSetting: function(obj) {
        //corpDt
        var rows = obj.rows;
        var datePicker = obj.datePicker;
        for (var k in rows) {
            if (Utils.isEmpty($("#" + k).data("type"))) {
                if (!Utils.isEmpty($("#" + k)[0])) {

                    if ($("#" + k)[0].type == 'text') {
                        $("#" + k).val(rows[k]);
                    }

                    if ($("#" + k)[0].type == 'number') {
                        $("#" + k).val(rows[k]);
                    }

                    if ($("#" + k)[0].type == 'textarea') {
                        $("#" + k).val(rows[k]);
                    }

                    if ($("#" + k)[0].type == 'hidden') {
                        if (Utils.isEmpty($("#" + k).val())) {
                            $("#" + k).val(rows[k]);
                        }
                    }

                    if ($("#" + k)[0].type == 'email') {
                        $("#" + k).val(rows[k]);
                    }

                    if ($("#" + k)[0].type == 'select-one') {
                        $("#" + k).val(rows[k]);
                    }

                    if ($("#" + k)[0].type == 'radio') {

                    }
                }
            } else {
                if ($("#" + k).data("type") == 'keyWord') {
                    $("#" + k).tagsinput('add', rows[k]);
                }
                if (!Utils.isEmpty(datePicker)) {
                    for (var i = 0; i < datePicker.length; i++) {
                        if (datePicker[i].id == k) {
                            if (!Utils.isEmpty(rows[k])) {

                                datePicker[i].obj.setDate(new Date(rows[k]));
                            }
                        }
                        //datePicker[i].setDate(new Date(rows[k]));
                    }
                    //datePicker.setDate(new Date(rows[k]));//$("#corpDt").val("1996-08-21");
                }
            }
        }
        return true;
    },
    /**
     * Html 값 셋팅
     */
    htmlSetting: function(obj) {
        var rows = obj.rows;
        for (var k in rows) {
            if (Utils.isEmpty($("#" + k).data("type"))) {
                if (!Utils.isEmpty($("#" + k)[0])) {
                    if (typeof rows[k] == "number") {
                        $("#" + k).html(Utils.thousandSeparatorCommas(rows[k]));
                    } else {
                        if (!Utils.isEmpty(rows[k])) {
                            if ($("#" + k)[0].type == 'hidden') {
                                $("#" + k).val(rows[k]);
                            } else {
                                $("#" + k).html(rows[k].replace(/\r\n/gi, "</br>"));
                            }
                        } else {
                            $("#" + k).html(rows[k]);
                        }
                    }
                }
            } else {
                if (!Utils.isEmpty($("#" + k)[0])) {
                    if ($("#" + k).data("type") == "textarea") {
                        if (Utils.isEmpty(rows[k])) {
                            $("#" + k).html(rows[k]);
                        } else {
                            $("#" + k).html(rows[k].replace(/(\n|\r\n)/g, "</br>"));
                        }

                    }
                }
            }
        }

        //리사이즈 이벤트
        resizeTwinTable();
    },
    /**
     * 작성페이지에서 수정 불가한 필드 변경
     * 
     * 
     */
    inputFormImpossible: function(obj) {
        var datePicker = obj.datePicker;
        var disableList = obj.disableList;
        if (disableList != undefined) {
            for (var i = 0; i < disableList.length; i++) {
                if (Utils.isEmpty($("#" + disableList[i]).data("type"))) {
                    $("#" + disableList[i]).prop("disabled", true);
                } else {
                    if (!Utils.isEmpty(datePicker)) {
                        for (var j = 0; j < datePicker.length; j++) {
                            if (disableList[i] == datePicker[j].id) {
                                datePicker[j].obj.disable();
                            }
                        }
                    }
                }
            }
        }
    },
    /**
     * CORP STEP 코드별로 IMG 와 클래스 리턴 
     * 
     * 
     */
    getCorpStepColor: function(obj) {
        var retnMap = new Object();
        if (obj == '100') {
            //작성중
            retnMap['cls'] = "g-color-deeporange";
            //retnMap['img'] = "/assets/img/icons/ckicon_deeporange.png";
        } else if (obj == '200') {
            //승인요청/승인대기
            retnMap['cls'] = "g-color-teal";
            //retnMap['img'] = "/assets/img/icons/ckicon_teal.png";
        } else if (obj == '300') {
            //보류
            retnMap['cls'] = "g-color-brown";
            //retnMap['img'] = "/assets/img/icons/ckicon_brown.png";
        } else if (obj == '400') {
            //거절
            retnMap['cls'] = "g-color-indigo";
            //retnMap['img'] = "/assets/img/icons/ckicon_indigo.png";
        } else if (obj == '500') {
            //승인완료
            retnMap['cls'] = "g-color-red";
            //retnMap['img'] = "/assets/img/icons/ckicon_red.png";
        }
        //000상태일떄는 나오면 안됨
        return retnMap;
    },
    /**
     * CORP STEP 코드별로 COLOR BADGE
     * 
     * 
     */
    getBadgeColor: function(obj) {
        var retnMap = new Object();
        if (obj == '100') {
            //작성중
            retnMap['cls'] = "g-badge-deeporange";
        } else if (obj == '200') {
            //승인요청/승인대기
            retnMap['cls'] = "g-badge-teal";
        } else if (obj == '300') {
            //보류
            retnMap['cls'] = "g-badge-brown";
        } else if (obj == '400') {
            //거절
            retnMap['cls'] = "g-badge-indigo";
        } else if (obj == '500') {
            //승인완료
            retnMap['cls'] = "g-badge-red";
        }
        //000상태일떄는 나오면 안됨
        return retnMap;
    },
    thousandSeparatorCommas: function(number) {
        var string = "" + number;  // 문자로 바꾸기. 

        string = string.replace(/[^-+\.\d]/g, "")  // ±기호와 소수점, 숫자들만 남기고 전부 지우기. 

        var regExp = /^([-+]?\d+)(\d{3})(\.\d+)?/;  // 필요한 정규식. 

        while (regExp.test(string)) string = string.replace(regExp, "$1" + "," + "$2" + "$3");  // 쉼표 삽입. 

        return string;
    },
    /**
     * Json data null check
     * @author JeongSeungHyeon
     * @version 1.0 2018/08/23 
     */
    jsonText: function(json) {
        if (typeof json === "undefined" || json === "null" || json === "" || (typeof json === "string" && json.trim() === "") || json === null) {
            return "-";
        } else {
            return json;
        }
    },
    /**
     * 이메일 정규표현식 체크
     */
    emailCheck: function( email ) { 
        var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/; 
        return (email != '' && email != 'undefined' && regex.test(email)); 
    },
    /**
     * 전화번호 정규표현식 체크 
     */
    hpNumCheck: function( hpNum ) { 
        var regex=/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/
        return (hpNum != '' && hpNum != 'undefined' && regex.test(hpNum)); 
    }

}

var Modal = {
    open: function(msg, callback1, callback2) {
        var message = "";
        var title = "";
        if (!Utils.isEmpty(msg) && typeof msg == "string") {
            message = msg;
        } else if (typeof msg == "object") {
            title = 'DB json 실행결과';
            message = msg.retnMsg + "(" + msg.retnCd + ")";
        } else {
            title = '알림';
            message = "메세지가 없습니다.";
        }

        if (!Utils.isEmpty(callback1) && jQuery.isFunction(callback1)) {
            setTimeout(function() {
                bootbox.confirm({
                    closeButton: false,
                    title: title,
                    message: message,
                    size: 'small', //small ,large
                    backdrop: undefined,
                    onEscape: false,
                    animate: true,
                    className: "bootbox-modal",
                    locale: "kr",
                    buttons: {
                        confirm: {
                            label: '확인',
                            className: 'u-btn-primary btn-md u-btn-hover-v1-1'
                        },
                        cancel: {
                            label: '취소',
                            className: 'u-btn-lightred btn-md u-btn-hover-v1-1'
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            if (!Utils.isEmpty(callback1) && jQuery.isFunction(callback1)) {
                                callback1();
                            } else {
                                return;
                            }
                        } else {
                            if (!Utils.isEmpty(callback2) && jQuery.isFunction(callback2)) {
                                callback2();
                            } else {
                                return;
                            }
                        }
                    }
                });
            }, 500);
        } else {
            setTimeout(function() {
                bootbox.alert({
                    closeButton: true,
                    title: '알림',
                    message: message,
                    size: 'small',
                    backdrop: true,
                    onEscape: true,
                    animate: true,
                    className: "bootbox-modal",
                    locale: "kr",
                    buttons: {
                        ok: {
                            label: '확인',
                            className: 'u-btn-primary btn-md u-btn-hover-v1-1'
                        }
                    }
                });
            }, 500);
        }
    }
}
/**
* 날짜관련 자바스크립트 공통함수
*
* 분단위 이하(= 초)는 고려하지 않았습니다.
* YYYYMMDDHHMI 형식의 String => 'Time'으로 칭함
*
* 주로 YYYYMMDD 까지만 쓰인다면 아래 함수들을
* YYYYMMDD 형식의 String => 'Date'로 하여 적당히
* 수정하시거나 아니면 함수를, 예를들어 isValidDate()처럼,
* 추가하시기 바랍니다.
*
* @version 2.0, 2001/01/28
* @author 박종진(JongJin Park), jongjpark@lgeds.lg.co.kr
*/


Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};


/**
* 마지막 날짜 리턴
*/

function lastdayOfMonth(calyear, calmonth) {
    var  dayOfMonth  = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
        if (((calyear %4 == 0) && (calyear % 100 != 0))||(calyear % 400 == 0))
            dayOfMonth[1] = 29;
        var nDays = dayOfMonth[calmonth-1];
    return nDays;
}

function firstDayOfMonth(date) { //moveTime(time,y,m,d,h)
	if(date != null && date != '' && date != 'undefined') {
	    date.setDate(1);		
	}

    return date;
}

/**
* 해당달의 날짜리스트 리턴
*/

function dayListOfMonth(calyear, calmonth) {
	var lastDay = lastdayOfMonth(calyear, calmonth);
	var dayList = new Array(lastDay);
	
	for(var no=1; no<lastDay+1; no++){
		var dayEle = "";
		if(no < 10) {
			dayEle = "0"+(no);
		} else {
			dayEle = no+"";
		}
		dayList[no-1] = dayEle;
	}
	
    return dayList;
}



/**
* 유효한(존재하는) 월(月)인지 체크
*/
function isValidMonth(mm) {
    var m = parseInt(mm,10);
    return (m >= 1 && m <= 12);
}

/**
* 유효한(존재하는) 일(日)인지 체크
*/
function isValidDay(yyyy, mm, dd) {
    var m = parseInt(mm,10) - 1;
    var d = parseInt(dd,10);

    var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
    if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
        end[1] = 29;
    }

    return (d >= 1 && d <= end[m]);
}

/**
* 유효한(존재하는) 시(時)인지 체크
*/
function isValidHour(hh) {
    var h = parseInt(hh,10);
    return (h >= 1 && h <= 24);
}

/**
* 유효한(존재하는) 분(分)인지 체크
*/
function isValidMin(mi) {
    var m = parseInt(mi,10);
    return (m >= 1 && m <= 60);
}

/**
* Time 형식인지 체크(느슨한 체크)
*/
function isValidTimeFormat(time) {
    return (!isNaN(time) && time.length == 12);
}

/**
* 유효하는(존재하는) Time 인지 체크

* ex) var time = form.time.value; //'200102310000'
*     if (!isValidTime(time)) {
*         alert("올바른 날짜가 아닙니다.");
*     }
*/
function isValidTime(time) {
    var year  = time.substring(0,4);
    var month = time.substring(4,6);
    var day   = time.substring(6,8);
    var hour  = time.substring(8,10);
    var min   = time.substring(10,12);

    if (parseInt(year,10) >= 1900  && isValidMonth(month) &&
        isValidDay(year,month,day) && isValidHour(hour)   &&
        isValidMin(min)) {
        return true;
    }
    return false;
}

/**
* Time 스트링을 자바스크립트 Date 객체로 변환
* parameter time: Time 형식의 String
*/
function toTimeObject(time) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);
    var hour  = time.substr(8,2);
    var min   = time.substr(10,2);

    return new Date(year,month,day,hour,min);
}


function toFormatTimeObject(time) {
	var myDate = time.split("-"); 
	return new Date(myDate[0],myDate[1]-1,myDate[2]);  
}

function toReplaceHyphenTimeString(timeString) {
	return timeString.replace(/-/gi, '');
}

function toReplaceCommaTimeString(timeString) {
	return timeString.replace(/,/gi, '');
}

function toReplaceTimeString(timeString, delimiter) {
	return timeString.replace('/'+delimiter+'/gi', '');
}

function dateToTimeString(strformat, date) { //moveTime(time,y,m,d,h)
    return date.format(strformat);
}

/**
* 자바스크립트 Date 객체를 Time 스트링으로 변환
* parameter date: JavaScript Date Object
*/
function toTimeString(date) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();
    var hour  = date.getHours();
    var min   = date.getMinutes();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
    if (("" + hour).length  == 1) { hour  = "0" + hour;  }
    if (("" + min).length   == 1) { min   = "0" + min;   }

    return ("" + year + month + day + hour + min);
}

function toFormatTimeString(date) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }

    return ("" + year +"-"+ month +"-"+ day);
}

function toJavaDateFormatTimeString(javaDate) { //formatTime(date)
	return toFormatTimeString(new Date(javaDate));
}

function timeStrToFormatTimeString2(timeStr) { //formatTime(string)
	
	if(timeStr == null || timeStr == '' || typeof timeStr == 'undefined') {
		return '';
	}
	
    var year  = timeStr.substr(0,4);
    var month = timeStr.substr(4,2);
    var day   = timeStr.substr(6,2);
    var hour   = timeStr.substr(8,2);
    var min   = timeStr.substr(10,2);
    var sec   = timeStr.substr(12,2);

    var outFormatTime = "" + year +"-"+ month;
    if((day+"").length > 0) {
    	outFormatTime = (outFormatTime+"-"+day);
    }
    if((hour+"").length > 0) {
    	outFormatTime = (outFormatTime+" "+hour);
    }
    if((min+"").length > 0) {
    	outFormatTime = (outFormatTime+":"+min);
    }
    if((sec+"").length > 0) {
    	outFormatTime = (outFormatTime+":"+sec);
    }
    
    return outFormatTime;
}


function timeStrToFormatTimeString(timeStr) { //formatTime(string)
	if(timeStr == null || timeStr == '' || typeof timeStr == 'undefined') {
		return '';
	}
	
    var year  = timeStr.substr(0,4);
    var month = timeStr.substr(4,2);
    var day   = timeStr.substr(6,2);

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }

    return ("" + year +"-"+ month +"-"+ day);
}

function timeStrHHMIToFormatTimeString(timeStr) { //formatTime(string)
	if(timeStr == null || timeStr == '' || typeof timeStr == 'undefined') {
		return '';
	}
	
    var hour  = timeStr.substr(0,2);
    var minutes = timeStr.substr(2,4);

    if (("" + hour).length == 1) { hour = "0" + hour; }
    if (("" + minutes).length   == 1) { minutes   = "0" + minutes;   }

    return (hour +":"+ minutes);
}

function timeStrToMonthlyFormatTimeString(timeStr) { //formatTime(string)
    var year  = timeStr.substr(0,4);
    var month = timeStr.substr(4,2);

    if (("" + month).length == 1) { month = "0" + month; }

    return ("" + year +"-"+ month);
}

function timeStrToMonthDayFormatTimeString(timeStr) { //formatTime(string)
    var month = timeStr.substr(4,2);
    var day   = timeStr.substr(6,2);

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }

    return (month +"/"+ day);
}

function toFormatTimeStringForObject(name) { //formatTime(date)
	var retVal;
	var value = name.value;
	
	if(value == '' || value == null ) {
		var date = new Date();
	    var year  = date.getFullYear();
	    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
	    var day   = date.getDate();

	    if (("" + month).length == 1) { month = "0" + month; }
	    if (("" + day).length   == 1) { day   = "0" + day;   }

		retVal = ("" + year +"-"+ month +"-"+ day);
	} else {
		retVal = value;
	}

	
    return retVal;
}
/**
* Time이 현재시각 이후(미래)인지 체크
*/
function isFutureTime(time) {
    return (toTimeObject(time) > new Date());
}

/**
* Time이 현재시각 이전(과거)인지 체크
*/
function isPastTime(time) {
    return (toTimeObject(time) < new Date());
}

function shiftDayString(strformat, time,y,m,d) { //moveTime(time,y,m,d,h)
    var date = toTimeObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return date.format(strformat);
}

/**
* 주어진 Time 과 y년 m월 d일 h시 차이나는 Time을 리턴

* ex) var time = form.time.value; //'20000101000'
*     alert(shiftTime(time,0,0,-100,0));
*     => 2000/01/01 00:00 으로부터 100일 전 Time
*/
function shiftTime(time,y,m,d,h) { //moveTime(time,y,m,d,h)
    var date = toTimeObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date);
}

/**
* 주어진 Time 과 y년 m월 d일 h시 차이나는 Time을 리턴

* ex) var time = form.time.value; //'20000101000'
*     alert(shiftTime(time,0,0,-100,0));
*     => 2000/01/01 00:00 으로부터 100일 전 Time
*/
function shiftTimeMin(time,y,m,d,h,min) { //moveTime(time,y,m,d,h)
    var date = toTimeObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함
    date.setMinutes(date.getMinutes() + min);       //min분를 더함

    return toTimeString(date);
}

function shiftTimeToDate(time,y,m,d,h) { //moveTime(time,y,m,d,h)
    var date = toTimeObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return date;
}

function shiftTimeMinToDate(time,y,m,d,h,min) { //moveTime(time,y,m,d,h)
    var date = shiftTimeToDate(time,y,m,d,h);
    date.setMinutes(date.getMinutes() + min);       //min분를 더함

    return date;
}


function shiftFormatMonthTime(time) { //moveTime(time,y,m,d,h)
    var date = toFormatTimeObject(time);

    var fullyear = date.getFullYear();
    var month = date.getMonth()+1;
    var lastDayofMonth = lastdayOfMonth(fullyear, month);
//    date.setDate(date.getDate() + d);         //d일을 더함
    date.setDate(lastDayofMonth);         //d일을 더함

    return toFormatTimeString(date);
}

function shiftFormatMonthTime(time,m) { //moveTime(time,y,m,d,h)
    var date = toFormatTimeObject(time);

    date.setMonth(date.getMonth() + m);       //m월을 더함
    var fullyear = date.getFullYear();
    var month = date.getMonth()+1;
    var lastDayofMonth = lastdayOfMonth(fullyear, month);
//    date.setDate(date.getDate() + d);         //d일을 더함
    date.setDate(lastDayofMonth);         //d일을 더함

    return toFormatTimeString(date);
}

function shiftFirstDayOfMonthFormatTime(time) { //moveTime(time,y,m,d,h)
    var date = toFormatTimeObject(time);
    date.setDate(1);         //d일을 더함

    return toFormatTimeString(date);
}


function shiftFormatDayTime(time,m,d) { //moveTime(time,y,m,d,h)
    var date = toFormatTimeObject(time);

    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return toFormatTimeString(date);
}


function shiftDayTime(date,m,d) { //moveTime(time,y,m,d,h)
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return date;
}

/**
* 두 Time이 몇 개월 차이나는지 구함

* time1이 time2보다 크면(미래면) minus(-)
*/
function getMonthInterval(time1,time2) { //measureMonthInterval(time1,time2)
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);

    var years  = date2.getFullYear() - date1.getFullYear();
    var months = date2.getMonth() - date1.getMonth();
    var days   = date2.getDate() - date1.getDate();

    return (years * 12 + months + (days >= 0 ? 0 : -1) );
}

function getFormatMonthInterval(time1,time2) { //measureMonthInterval(time1,time2)
    var date1 = toFormatTimeObject(time1);
    var date2 = toFormatTimeObject(time2);

    var years  = date2.getFullYear() - date1.getFullYear();
    var months = date2.getMonth() - date1.getMonth();
    var days   = date2.getDate() - date1.getDate();

    return (years * 12 + months + (days >= 0 ? 0 : -1) );
}


function getFormatMonthDiffer(time1,time2) {
	var result = false;
	
    var date1 = toFormatTimeObject(time1);
    var date2 = toFormatTimeObject(time2);

	var years1 = date1.getFullYear();
	var years2  = date2.getFullYear();
	var months1 = date1.getMonth()+1;
    var months2 = date2.getMonth()+1;

//alert("[date1]:"+years1+""+months1+"		[date2]:"+years2+""+months2);

	if( (years1+""+months1) == (years2+""+months2) ) {
		result = true;
	}    

    return result;
}


/**
* 두 Time이 며칠 차이나는지 구함
* time1이 time2보다 크면(미래면) minus(-)
*/
function getDayInterval(time1,time2) {
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);
    var day   = 1000 * 3600 * 24; //24시간

    return parseInt((date2 - date1) / day, 10);
}

/**
* 두 Time이 몇 시간 차이나는지 구함

* time1이 time2보다 크면(미래면) minus(-)
*/
function getHourInterval(time1,time2) {
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);
    var hour  = 1000 * 3600; //1시간

    return parseInt((date2 - date1) / hour, 10);
}

/**
* 두 Time이 몇 분 차이나는지 구함
* 인자 : YYYYMMDDHHMI 포맷 스트링
* time1이 time2보다 크면(미래면) minus(-)
*/
function getMinInterval(time1,time2) { //moveTime(time,y,m,d,h)
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);
    var min  = (1000 * 3600) / 60; //1분

    return parseInt((date2 - date1) / min, 10);
}

/**
* 현재 시각을 Time 형식으로 리턴

*/
function getCurrentTime() {
    return toTimeString(new Date());
}

/**
* 현재 시각과 y년 m월 d일 h시 차이나는 Time을 리턴
*/
function getRelativeTime(y,m,d,h) {
/*
    var date = new Date();

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date);
*/
    return shiftTime(getCurrentTime(),y,m,d,h);
}


/**
* 현재 시각과 y년 m월 d일 h시 차이나는 Time을 리턴
*/
function getRelativeTime(y,m,d,h,min) {
/*
    var date = new Date();

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date);
*/
    return shiftTimeMin(getCurrentTime(),y,m,d,h,min);
}


function getRelativeDate(y,m,d,h,min) {
/*
    var date = new Date();

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date);
*/
    return shiftTimeMinToDate(getCurrentTime(),y,m,d,h,min);
}

/**
* 현재 年을 YYYY형식으로 리턴
*/
function getYear() {
/*
    var now = new Date();
    return now.getFullYear();
*/
    return getCurrentTime().substr(0,4);
}

/**
* 현재 月을 MM형식으로 리턴
*/
function getMonth() {
/*
    var now = new Date();

    var month = now.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    if (("" + month).length == 1) { month = "0" + month; }

    return month;
*/
    return getCurrentTime().substr(4,2);
}

/**
* 현재 日을 DD형식으로 리턴

*/
function getDay() {
/*
    var now = new Date();

    var day = now.getDate();
    if (("" + day).length == 1) { day = "0" + day; }

    return day;
*/
    return getCurrentTime().substr(6,2);
}

/**
* 현재 時를 HH형식으로 리턴
*/
function getHour() {
/*
    var now = new Date();

    var hour = now.getHours();
    if (("" + hour).length == 1) { hour = "0" + hour; }

    return hour;
*/
    return getCurrentTime().substr(8,2);
}

function getMinutes() {
    return getCurrentTime().substr(10,2);
}


/**
* 오늘이 무슨 요일이야?

* ex) alert('오늘은 ' + getDayOfWeek() + '요일입니다.');
* 특정 날짜의 요일을 구하려면? => 여러분이 직접 만들어 보세요.
*/
function getDayOfWeek() {
    var now = new Date();

    var day = now.getDay(); //일요일=0,월요일=1,...,토요일=6
    var week = new Array('일','월','화','수','목','금','토');

    return week[day];
}


function getYearList(startYear) {
	var yearList = new Array();
	var currentYear = getYear();
	
	for(var i=startYear; i<=currentYear; i++) {
		if( !yearList.contains(i) ) {
			yearList.push(i);				
		}
	}
	return yearList;
}

function getYearListFromTo(startYear, endYear) {
	var yearList = new Array();
	var currentYear = endYear;
	
	for(var i=startYear; i<=currentYear; i++) {
		if( !yearList.contains(i) ) {
			yearList.push(i);				
		}
	}
	return yearList;
}


function getMonthListOfYear() {
	return new Array("01","02","03","04","05","06","07","08","09","10","11","12");
}

function get24HourList() {
	return new Array("00","01","02","03","04","05","06","07","08","09","10","11","12"
			,"13","14","15","16","17","18","19","20","21","22","23");
}

function getMinList() {
	var latMin = 60;
	var minList = new Array(latMin);
	
	for(var no=0; no<latMin; no++){
		var minEle = "";
		if(no < 10) {
			
			minEle = "0"+(no);
		} else {
			minEle = no+"";
		}
		minList[no] = minEle;
	}
	
    return minList;
//	return new Array("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20"
//			,"21","22","23","24","25","26","27","28","29","30","31","32","33","44","15","16","17","18","19","20");
}

//DATE 형식 체크 함수
function fncChkDate(obj) {
	var sDate = $(obj).val();
	var sOrgDate, sPatt;
	var sYear = "", sMonth = "", sDay = "";   
	var iYear = 0, iMonth = 0, iDay = 0;
		
	sPatt = /\//g; sDate = sDate.replace(sPatt,"");
	sPatt = /-/g;  sDate = sDate.replace(sPatt,"");
	sPatt = /\./g; sDate = sDate.replace(sPatt,"");

	if (sDate == "") {
		//alert("날짜를 입력 해 주세요.");
		$(obj).val("");
		//$(obj).focus();
		return "";
	}
	
	if (sDate.length != 8) {
		alert("날짜를 YYYYMMDD 형태로 입력 해 주세요.");
		$(obj).val("");
		$(obj).focus();
		return "";
	} else {
		sYear = sDate.substring(0,4);
		sMonth = sDate.substring(4,6); 
		sDay = sDate.substring(6,8); 
	}  

	if (isNaN(sYear) || isNaN(sMonth) || isNaN(sDay)) {
		alert("날짜를 YYYYMMDD 형태로 입력 해 주세요.");
		$(obj).val("");
		$(obj).focus();
		return "";
	}

	iYear = parseInt(sYear,'10');
	iMonth = parseInt(sMonth,'10');
	iDay = parseInt(sDay,'10'); 

	if (iYear < 1) iYear = 0;
	if (iMonth < 1 || iMonth > 12)  iMonth = 0;
	if (iDay < 1) iDay = 0;

	if ( iMonth == 1 || iMonth == 3 || iMonth == 5 || iMonth == 7 || iMonth == 8 || iMonth == 10 || iMonth == 12)  { 
		if (iDay > 31) {
			iDay = 0;  
		}
	} else if (iMonth == 4 || iMonth == 6 ||  iMonth == 9 || iMonth == 11) {
		if (iDay > 30) {
			iDay = 0;  
		}
	} else if (iMonth == 2 )  {
		if (iYear % 4 != 0 || (iYear % 100 == 0 && iYear % 400 != 0)) {
			if (iDay > 28) {
				iDay = 0;  
			}
		} else if (iDay > 29) {
			iDay = 0;  
		}
	}
	
	if (iYear == 0 || iMonth == 0 || iDay == 0) {
		alert("날짜를 YYYYMMDD 형태로 입력 해 주세요.");
		$(obj).val("");
		$(obj).focus();
		return "";
	} else {
		$(obj).val(sYear + "-" + sMonth + "-" + sDay);
		return sYear + "-" + sMonth + "-" + sDay;
	}
}

//날짜 from ~ to 체크 
function fncFrToDate(fromDt, toDt) {
	fromDt = toReplaceHyphenTimeString(fromDt);
	toDt = toReplaceHyphenTimeString(toDt);
	
	sPatt = /\//g; fromDt = fromDt.replace(sPatt,"");
	sPatt = /-/g;  fromDt = fromDt.replace(sPatt,"");
	sPatt = /\./g; fromDt = fromDt.replace(sPatt,"");
	sPatt = /\//g; toDt = toDt.replace(sPatt,"");
	sPatt = /-/g;  toDt = toDt.replace(sPatt,"");
	sPatt = /\./g; toDt = toDt.replace(sPatt,"");
	
	if (fromDt > toDt) {
		return false;
	} else {
		return true;
	}
}

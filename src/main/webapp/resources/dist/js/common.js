//==================================================================================================
//form -> json
//==================================================================================================
(function($){
	$.fn.serializeFORMJSON = function(){
		var o = {};
		var a = this.serializeArray();
		$.each(a, function(){
			  if(o[this.name]){
				  if(!o[this.name].push){
					  o[this.name] = [o[this.name]];
				  }
				  o[this.name].push(this.value || '');
			  }else{
				  o[this.name] = this.value || '';
			  }
		});
		return o;
	};
})(jQuery);

$.extend( $.fn, {
	createTableRow : function($tr, data, afterHandler) {
		var rows = [];
		$.each(data, function(i, obj) {
			var $row = $tr.clone();
			$tr.find('td').each(function(j) {
				$(this).html(obj[j]||'');
			});
			rows.push($tr[0].outerHTML);
		});
		var $tbl = this;
		$tbl.find('tbody').empty().append(rows.join(''));
		
		if (afterHandler) {
			$.each(afterHandler, function(i, obj) {
				$tbl.find('tbody').find(obj[0]).off('click').on('click', (obj[1]));
			});
		}
	}
	,
	appendTableRow : function($tr, data, afterHandler) {
		var rows = [];
		$.each(data, function(i, obj) {
			var $row = $tr.clone();
			$tr.find('td').each(function(j) {
				$(this).html(obj[j] || '');
			});
			rows.push($tr[0].outerHTML);
		});
		var $tbl = this;
		$tbl.find('tbody').append(rows.join(''));
		
		if (afterHandler) {
			$.each(afterHandler, function(i, obj) {
				$tbl.find('tbody').find(obj[0]).off('click').on('click', (obj[1]));
			});
		}
	}
});

$(function() {
	$('#navTitle').text('TODO 공통 처리 헤더');
});

function createTableRow(cells) {
    var tds = cells.map(function (cellContent) {
   		return '<td>' + ( cellContent == null ? "" : cellContent) + '</td>';
    }).join('');
    return '<tr>' + tds + '</tr>';
}

/**
 * 날짜 유효성 체크 후 날짜를 locale에 맞게 변경
 */
$.setDateFormat = function() {
	$('.format-date-time, .format-date').each(function() {
		var format = $(this).hasClass('format-date-time') ? 'LLL' : 'L';
		if (this.tagName == 'INPUT') {
			var momentDate = moment($(this).val());
			if (momentDate.isValid()) {
				$(this).val(momentDate.format(format));
			}
		} else {
			var momentDate = moment($(this).text());
			if (momentDate.isValid()) {
				$(this).text(momentDate.format(format));
			}
		}
	});
}

$.setNumberFormat = function() {
	$('.number-format').each(function(i) {
		if (this.tagName == 'INPUT') {
			$(this).val(fnAddComma($(this).val()));
		} 
		else {
			$(this).html(fnAddComma($(this).text()));
		}
	});
}

/*
 * 문자열의 {*} 값을 args로 치환한다.
 */
function convertMessage(msg, args) {
	if (msg == null) {
		return null;
	}
	if (args == null) {
		return msg
	}
	
	if (arguments.length > 2) {
		for (var i = 0; i < arguments.length - 1; i++) {
			var regex = '/\\{' + i + '\\}/g';
			msg = msg.replace(eval(regex), arguments[i + 1]);
		}
	}
	return msg;
}

/* 
 * 같은 값이 있는 열을 병합함
 * 사용법 : $('#테이블 ID').rowspan(병합하고 싶은 열번호);
 */     
$.fn.rowspan = function(colIdx, isStats) {       
	return this.each(function(){      
		var that;     
		$('tr', this).each(function(row) {      
			$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
				
				if ($(this).html() == $(that).html()
					&& (!isStats || isStats && $(this).prev().html() == $(that).prev().html())) {            
					rowspan = $(that).attr("rowspan") || 1;
					rowspan = Number(rowspan)+1;

					$(that).attr("rowspan",rowspan);
					
					// do your action for the colspan cell here            
					$(this).hide();
					
					//$(this).remove(); 
					// do your action for the old cell here
					
				} else {            
					that = this;         
				}          
				
				// set the that if not already set
				that = (that == null) ? this : that;      
			});     
		});    
	});  
}; 

$.currentTime = function(callback) {
	$.ajaxLotteryGet('/cmm/currentTime', null, function(data) {
		if (typeof callback == 'function') {
			callback(data);
		} else {
			return moment(data + '').format('LLL');
		}
	});
}

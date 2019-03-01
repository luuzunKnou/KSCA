//input 체크
function check(reg, dest, msg) {
	if(!reg.test(dest.val())){
	    alert(msg);
	    dest.val("");
	    dest.focus(); 
	    
	    return false;
	}
	else {
		return true;
	}
}

//n에 width 자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
}
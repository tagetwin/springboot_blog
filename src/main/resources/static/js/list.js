$('body').on('click', '.pno',function (){
	var tr = $(this);
	var td = tr.children();
	var no = td.eq(0).text();
	location.href='/post/detail/'+no;
});

function url(page, range){
	var url = `/post?page=${page}&range=${range}`;
	location.href=url;
}

// 현재
function fn_pagination(page, range, rangeSize, searchType, keyword) {
	url(page, range);
}
// 이전
function fn_prev(page, range, rangeSize) {
	var page = ((range - 2) * rangeSize) + 1;
	var range = range - 1;
	url(page, range);
}

//다음 버튼 이벤트
function fn_next(page, range, rangeSize) {
	var page = parseInt((range * rangeSize)) + 1;
	var range = parseInt(range) + 1;
	url(page, range);
}

//function res(r){
//var res = '';
//for(key in r){
//	res += `<tr class="pno">`;
//	res += `<td>${r[key].id}</td>`;
//	res += `<td>${r[key].title}</td>`;
//	res += `<td>${r[key].username}</td>`;
//	res += `<td>${r[key].createDate}</tr>`;
//}
//$('#tbody').append(res);
//}
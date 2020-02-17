	$('#searchForm button').on('click', function(event){
		event.preventDefault();
		var searchForm = $('#searchForm');
		
		
		if(!searchForm.find('option:selected').val()){
			alert('검색종류를 선택하세요');
			return false
		}
		
		if(!searchForm.find("input[name='keyword']").val()){
			alert('키워드를 입력하세요');
			return false;
		}
		searchForm.find("input[name='page']").val(1);
		searchForm.submit();

	});

	var jobForm = $('#jobForm');
	$('#pagenate a').on('click', function(event){
		event.preventDefault();
		
		var targetPage = $(this).attr('href');
		jobForm = $('#jobForm');
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr('action','/post').attr('method','get');
		jobForm.submit();
		
	});

	$('.read').on('click', function(event){
		event.preventDefault();
		jobForm = $('#jobForm');
		jobForm.append("<input type='hidden' name='id' value='"
				+$(this).attr("href")+"'/>");
		jobForm.attr('action','/post/detail').attr('method','get');
		jobForm.submit();
		
	})	
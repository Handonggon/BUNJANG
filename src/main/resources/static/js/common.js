$(function() {
	var loading = $('<div id="loading" style="text-align: center; background-color: rgb(0, 0, 0, 0.3); width: 100%; height: 100%; position: fixed; z-index: 2147483647;"><i class="g-absolute-centered fas fa-sync-alt fa-spin fa-3x fa-fw" style="top: 50%; position: absolute; width: 50px; height: 50px;"></i></div>').prependTo(document.body).hide();
	
	var showCount = 0;
	
	//요청 인터셉터 추가하기
	axios.interceptors.request.use(function(config) {
		if(showCount++ === 0) {
			loading.show();
		}
		return config;
	}, function(error) {
		let data = {success: false
				  , message: error};
		return data;
	});

	// 응답 인터셉터 추가하기
	axios.interceptors.response.use(function(response) {
		if(--showCount === 0) {
			loading.hide();
		}
		return response;
	}, function(error) {
		if(--showCount === 0) {
			loading.hide();
		}
		let data = {success: false
				  , message: error};
		return {
			status: 404,
			data: data
		};
	});
	
	/* Number Check */
	$('.isNumberCheck').keyup(function(obj) {
		inputNumberChk(obj.currentTarget.id);
	});
});
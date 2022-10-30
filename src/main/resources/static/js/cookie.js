var Cookie = {
	setCookie: function(name, value, options = {}) {
		options = {
			path: '/',
			...options
		};
		
		if(options.expires instanceof Date) {
			options.expires = options.expires.toUTCString();
		}
		
		let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);
		
		for(let optionKey in options) {
			updatedCookie += "; " + optionKey;
			let optionValue = options[optionKey];
			if(!optionValue) {
				updatedCookie += "=" + optionValue;
			}
		}
		document.cookie = updatedCookie;
	},
	
	getCookie: function(name) {
		let matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
		return matches ? decodeURIComponent(matches[1]) : undefined;
	},
	
	hasCookie: function(name) {
		return !(typeof Cookie.getCookie(name) === "undefined");
	},
	
	delCookie: function(name) {
		Cookie.setCookie(name, null, {'max-age': 0});
	}
}
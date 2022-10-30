import {TUI_UTILS} from './TuiUtils.js';

    /********************************************************************************
     * Documents : https://nhn.github.io/toast-ui.select-box/latest/
     * 		key						type					desc
     * ------------------------------------------------------------------------------
     * Parameters -------------------------------------------------------------------
     * ------------------------------------------------------------------------------
     * container					HTMLElement | string	대상 요소
     * options						object					옵션
     * 	data						list					
     * 		1) itemGroupData		object					
     * 			label				string					라벨
     * 			data				list					options > data > 2) itemData 참조
     * 			disabled			boolean[false]			비활성화 여부
     * 		2) itemData				object					
     * 			label				string					라벨
     * 			value				string					값
     * 			disabled			boolean[false]			비활성화 여부
     * 			selected			boolean[false]			선택 여부
     * 	itemSelect'					object					
     * 		url'					string					GET URL
     * 		params'					object					파라미터
     * 		processing'				function				데이터 가공 함수
     * 	allOption'					boolean[false]			전체항목 표시 여부
     * 	placeholder					string[""]				
     * 	disabled					boolean[false]			비활성화 여부
     * 	autofocus					boolean[true]			선택창 포커스 표시 여부
     * 	autoclose					boolean[true]			(선택)자동 닫기 여부
     * 	showIcon					boolean[true]			화살표 아이콘 표시 여부
     * 	theme						object					테마
     * 	usageStatistics				boolean					통계 데이터 수집 허용 여부(false)
     * ------------------------------------------------------------------------------
     * Methods ----------------------------------------------------------------------
     * ------------------------------------------------------------------------------
     * 	on/off												사용자 정의 이벤트 추가/제거
     * 		eventName				string					이벤트 이름('change', 'close', 'disable', 'enable', 'open')
     * 		listener				function				이벤트 처리 핸들러
     * 	close												
     * 	open												
     * 	deselect											
     * 	destroy												
     * 	disable												
     * 		value					string|number|Item|ItemGroup
     * 	enable												
     * 		value					string|number|Item|ItemGroup
     * 	focus												
     * 	getItem												
     * 		value					string|number			
     * 	getItemGroup										
     * 		index					number					
     * 	getItemGroups										
     * 		callback				function				
     * 		number					number					
     * 	getItems											
     * 		callback				function				
     * 		number					number					
     * 	getSelectedItem										
     * 	select												
     * 		value					string|number|Item
     * 	toggle						
     * *******************************************************************************/
window["TuiSelectBoxAdapter"] = class TuiSelectBoxAdapter {
	#BAES_THEME = {
		'common.border': '1px solid #ededed',
		'common.color': '#353535',
		'common.background': '#ffffff',

		'common.disabled.background': '#f1f1f1',
		'common.disabled.color': '#d8d8d8',
		
		'itemGroup.items.paddingLeft': '15px',
		
		'itemGroup.label.color': 'black',
		
		'item.selected.background': '#b3ca9a',
		'item.selected.color': 'white',
		
		'item.highlighted.background': '#fbf3c1'
	}
	
	constructor(container, option={}) {
		option["autofocus"] = TUI_UTILS.NVL(option["autofocus"], true);
		option["autoclose"] = TUI_UTILS.NVL(option["autofocus"], true);
		option["showIcon"] = TUI_UTILS.NVL(option["showIcon"], true);
		
		option["theme"] = TUI_UTILS.NVL(option["theme"], this.#BAES_THEME);
		option["theme"]['common.width'] = $(container).width() + 'px';
		option["theme"]['common.height'] = $(container).height() + 'px';
		
		option["usageStatistics"] = false;
		
		return (async () => {
			try {
				let data = await new Promise((resolve, reject) => {
					if(TUI_UTILS.isEmpty(option["itemSelect"])) {
						resolve(option["data"]);
					} else {
						let {url, params, processing} = option["itemSelect"];
						
						axios({
							method: 'get',
							url: url,
							params: params
						}).then(function(response) {
							if(response["data"]["success"]) {
								resolve(TUI_UTILS.NVL(option["data"], []).concat(TUI_UTILS.NVL(processing, (date)=>date)(response["data"]["list"])));
							} else {
								reject(response["data"]["message"]);
							}
						});
					}
				});
				if(TUI_UTILS.NVL(option["allOption"], false)) {
				data.unshift({ label: '전체', value: '' });
				}
				option["data"] = data;
				
				return new Proxy(new tui.SelectBox(container, option), {
					get: (obj, prop)=>{
						//console.log(obj, prop);
						return obj[prop];
					},
					set: (obj, prop, value)=>{
						//console.log(obj, prop, value);
						obj[prop] = value;
					}
				});
			} catch(message) {
				alert(message);
			}
		})();
	}
}
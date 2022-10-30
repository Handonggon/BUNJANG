class CustomPopup {
	constructor(popupId) {
		this.div = document.getElementById(popupId);
		this.pText = this.div.querySelector(".popupText");
		
		this.buttonList = [
			/* element, result */
			[this.div.querySelector(".popupButtonConfirm"), true],
			[this.div.querySelector(".popupButtonCancel"), false],
		]
	}
	
	/* private method */
	#Utils = {
		isEmpty: function(obj) {
			return (obj === undefined || obj === null || (typeof obj === "string" && obj.trim() === "") || obj.length === 0);
		},
		NVL: function(o, t) {
			if(Utils.isEmpty(o)) return t;
			return o;
		},
	}
	
	setText = (text)=>{
		this.pText.innerHTML = this.#Utils.NVL(text, "");
	}
	
	open = (text, callBack = (r, e)=>{})=>{
		this.setText(text);
		
		for(let button of this.buttonList) {
			let [element, result] = button;
			if(!this.#Utils.isEmpty(element)) {
				let listener = function(e) {
					callBack(result, e);
					this.close();
				}.bind(this);
				
				element.addEventListener('click', listener);
				button.push(listener);
			}
		}
		this.div.style.display="block";
	}
	
	close = ()=>{
		this.pText.innerHTML = "";
		
		for(let button of this.buttonList) {
			let [element, result, listener] = button;
			if(!this.#Utils.isEmpty(listener)) {
				element.removeEventListener('click', listener);
			}
		}
		
		this.div.style.display="none";
	}
}

class GridPopup {
	constructor(option) {
		this.div = document.createElement("div");
		this.div.style.display = "none";
		this.div.setAttribute("id","test");
		this.popup = document.getElementById("gridPopup");
		this.popup.querySelector(".boxContents").insertBefore(this.div, this.popup.querySelector(".boxContents").firstChild);
		
		this.buttonList = [
			/* element, result */
			[this.popup.querySelector(".popupButtonConfirm"), true],
			[this.popup.querySelector(".popupButtonCancel"), false, (r, e)=>{
				this.close();
			}],
		]
		
		this.checkBox = this.#Utils.NVL(option?.checkBox, false);
		if(this.checkBox) {
			option.rowHeaders = this.#Utils.NVL(option.rowHeaders, []).concat({type: 'checkbox', width: 60, align: 'center', valign: 'center', header: '<div></div>'});
			this.popup.querySelector(".popupButtonConfirm").style.display = "inline-block"
		} else {
			this.popup.querySelector(".popupButtonConfirm").style.display = "none"
		}
		this.grid = new TuiGridAdapter('test', option);
	}
	
	/* private method */
	#Utils = {
		isEmpty: function(obj) {
			return (obj === undefined || obj === null || (typeof obj === "string" && obj.trim() === "") || obj.length === 0);
		},
		NVL: function(o, t) {
			if(Utils.isEmpty(o)) return t;
			return o;
		},
	}
	
	
    /********************************************************************************
     * 		key						type					desc
     * ------------------------------------------------------------------------------
     * callBackList -------------------------------------------------------------------
     * ------------------------------------------------------------------------------
     * type							string					입력 형태('SEARCH', 'GRID')
     * 1) SEARCH											
     * 		field					string					입력 필드
     * 		tartger					string					대상 요소 쿼리
     * 		processing				function				데이터 전처리[(d)=>d.join(", ")]
     * 2) GRID												
     * 		field					string					입력 필드
     * 		rowKey					int						대상 row
     * 		tartger					string					대상 필드
     * 		processing				function				데이터 전처리[(d)=>d.join(", ")]
     * *******************************************************************************/
	open = (url, params={}, callBackList=[])=>{
		if(this.checkBox) {
			this.grid.on("click", function(e) {
				let {rowKey, targetType, instance} = e;
				if(targetType !== "cell") return false;
				
				instance.check(rowKey);
			});
			
			this.buttonList[0].push(function(e) {
				let instance = this.grid;
				let checkedRows = instance.getCheckedRows();
				
				for(let callBack of callBackList) {
					callBack["processing"] = this.#Utils.NVL(callBack["processing"], (d)=>d.join(", "));
					
					switch(callBack["type"].toUpperCase()) {
						case "SEARCH": {
							document.querySelector(callBack["tartger"]).value = callBack["processing"](checkedRows.map(row=>row[callBack["field"]]));
							break;
						}
						case "GRID": {
							callBack["instance"].setValue(callBack["rowKey"], callBack["tartger"], callBack["processing"](checkedRows.map(row=>row[callBack["field"]])));
							break;
						}
					}
				}
				this.close();
			}.bind(this));
		} else {
			this.grid.on("click", function(e) {
				let {rowKey, targetType, instance} = e;
				if(targetType !== "cell") return false;
				
				let rowData = instance.getRow(rowKey);
				
				for(let callBack of callBackList) {
					switch(callBack["type"].toUpperCase()) {
						case "SEARCH": {
							document.querySelector(callBack["tartger"]).value = rowData[callBack["field"]];
							break;
						}
						case "GRID": {
							callBack["instance"].setValue(callBack["rowKey"], callBack["tartger"], rowData[callBack["field"]]);
							break;
						}
					}
				}
				this.close();
			}.bind(this));
		}
		
		for(let button of this.buttonList) {
			let [element, result, listener] = button;
			if(!this.#Utils.isEmpty(listener)) {
				element.addEventListener('click', listener);
			}
		}
		
		this.grid.select(url, params);
		this.div.style.display="block";
		this.popup.style.display="block";
	}
	
	close = ()=>{
		for(let button of this.buttonList) {
			let [element, result, listener] = button;
			if(!this.#Utils.isEmpty(listener)) {
				element.removeEventListener('click', listener);
			}
		}
		
		this.grid.off("click");
		this.grid.clear();
		this.div.style.display="none";
		this.popup.style.display="none";
	}
}
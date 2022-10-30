export const TUI_UTILS = {
	isEmpty: (s)=>(s===undefined || s===null || s==='undefined' || s==='null' || s===""),
	NVL: (s, r)=>TUI_UTILS.isEmpty(s) ? r : s,
	MakeUrl: (url, param)=>(typeof param === "object") ? url + "?" + Object.keys(param).map(key=>key + "=" + param[key]).join("&") : url,
	sleep: (delay)=>new Promise((resolve)=>setTimeout(resolve, delay)),
	comma: (n)=>{
		let [num, decimal] = n.toString().split("."); 
		return num.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (TUI_UTILS.isEmpty(decimal) ? "" : ("." + decimal));
	},
	formatD: (v, f)=>parseFloat(Math.round(v * 100) / 100).toFixed(f),
}
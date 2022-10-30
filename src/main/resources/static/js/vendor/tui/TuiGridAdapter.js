import {TUI_UTILS} from './TuiUtils.js';

    /********************************************************************************
     * Documents : http://nhn.github.io/tui.grid/latest/
     *      key                     type            desc
     * --------------------------------------------------------------------------------
     * width                        int             그리드 넓이 [default: 'auto']
     * bodyHeight                   int             그리드 높이 [default: 250]
     * rowHeight                    int             그리드 Row 높이 [default: 40]
     * scrollX                      boolean         수평 스크롤 여부 [default: false]
     * scrollY                      boolean         수직 스크롤 여부 [default: true]
     *                                              
     * columns                      list            column list
     *  column(=item)               object          
     *      header                  string          column header
     *      name                    string          column 이름
     *      dataType                string          데이터 타입 [default: string] (string, number, date, boolean)
     *      formatter               function        포맷 지정 함수(value)
     *          dateFormat          string          날짜 모맷 [default: YYYYMMDD] (YYYYMMDDHHMMSS)
     *      width                   int             column 넓이 [default: 'auto']
     *      align                   string          수평 정렬   [default: center] (left, center, right)
     *      valign                  string          수직 정렬 [default: middle] (top, middle, bottom)
     *      className               string          적용할 클래스 이름
     *      hidden                  boolean         column 숨김 여부[default: false]
     *      sortable                boolean         column 정렬기능 사용여부[default: false]
     *      disabled                boolean         column 비활성화 여부[default: false]
     *      validation              object          값 체크
     *          required            boolean         필수값 여부 [[default: false]
     *                                              
     * header                       object          
     *  complexColumns              list            
     *      item                    object          
     *          header              string          column header와 동일
     *          name                string          column name과 동일
     *          childNames          list<string>    합쳐질 column name list
     *  height                      int             header 높이 [default: 40]
     * 
     * data                         list            data list
     *  item                        object          
     *      key                     string          column name
     *      value                   object          해당 column의 값
     *                                              
     * rowHeaders                   list            add column set(체크박스, Seq 등..)
     *  item                        object          
     *      type                    string          column 타입 (checkbox, rowNum)
     *      width                   int             column 넓이 [default: 'auto']
     *      align                   string          수평 정렬   [default: center] (left, center, right)
     *      valign                  string          수직 정렬 [default: middle] (top, middle, bottom)
     *      header                  html            해더 render html
     *                                              
     * selectionUnit                string          클릭시 선택되는 단위 [default: cell] (cell, row)
     * 
     * editor                       text            editor Type (text, datePicker)
     * 
     * 
     * 
     * pageOptions                  object              페이징 설정
     *  perPage                     int                 페이징 row count
     * *******************************************************************************/
window["TuiGridAdapter"] = class TuiGridAdapter {
    
    constructor(container, option={}) {
        option["el"] = document.getElementById(container);
        
        this.grid = new tui.Grid(option);
        
        /* 메서드 */
        this.grid.select = async function(url, params={}, processing=(d)=>d) {
            let response = await axios({
                method: 'get',
                url: url,
                params: params
            });
            if(response["data"]["success"]) {
                let keyList = this.grid.getColumns().map(_column=>_column["name"]);
                let dataList = response["data"]["list"].map(_row=>Object.fromEntries(Object.entries(_row).filter(_entry=>keyList.includes(_entry[0]))));
                this.grid.resetData(processing(dataList));
            } else {
                alert(response["data"]["message"]);
            }
        }.bind(this);
        
        this.grid.on('onGridUpdated', function(e) {
            let {instance} = e;
            instance.refreshLayout();
        });
        
        this.grid.getCheckedRow = function(index=0) {
            let checkedRows = this.grid.getCheckedRows();
            if(checkedRows.length === 0) return null;
            return checkedRows[(checkedRows.length + index)%checkedRows.length];
        }.bind(this);
        
        /* 편집 시 포커스를 잃을 경우 자동 커밋 */
        this.grid.on('editingStart', function(e) {
            let {rowKey, columnName, instance} = e;
            setTimeout(function() {
                let editor = instance["el"].querySelector(".tui-grid-cell-content-editor")
                if(TUI_UTILS.isEmpty(editor)) return false;
                let input = editor.querySelector("input");
                let blurListener = function() {
                    instance.finishEditing(rowKey, columnName, editor.value);
                }
                input.addEventListener('blur', blurListener);
                
                let finishListener = function(t) {
                    TUI_UTILS.NVL(t.instance?.emailListener, ()=>{})({
                        rowKey: t.rowKey,
                        columnName: t.columnName,
                        value: t.value,
                        instance: t.instance
                    });
                    
                    input.removeEventListener('blur', blurListener);
                    instance.off('editingFinish', finishListener);
                }
                instance.on('editingFinish', finishListener);
            }, 300);
        });
        
        this.grid = new Proxy(this.grid, {
            get: (obj, prop)=>{
                //console.log(obj, prop);
                return obj[prop];
            },
            set: (obj, prop, value)=>{
                //console.log(obj, prop, value);
                obj[prop] = value;
            }
        });
        
        return this.grid;
    }
}

class Column {
    constructor(props) {}
    
    getElement() {
        return this.el;
    }
    
    render(props) {
        this.el.innerHTML = TUI_UTILS.NVL(String(props.value), "");
    }
}

class Header {
    constructor(props) {}
    
    getElement() {
        return this.el;
    }
    
    render(props) {
        this.el.innerHTML = TUI_UTILS.NVL(String(props.columnInfo.header), "");
    }
}

class Editor {
    constructor(props) {}
    
    getElement() {
        return this.el;
    }

    getValue() {
        return this.el.value;
    }

    mounted() {
        this.el.select();
    }
    
    render(props) {
        this.el.value = TUI_UTILS.NVL(String(props.value), "");
    }
}

/* Render Class - Header */
window["PlusBtnColumnHeader"] = class PlusBtnColumnHeader extends Header {
    constructor(props) {
        super(props);
        this.el = document.createElement("button");
        this.el.type = 'plusBtn';
        this.el.className = 'plus';
        this.el.addEventListener('click', function(e) {
            props.grid.appendRow({}, {
                focus: true,
            });
        });
    }
}

window["requiredColumnHeader"] = class requiredColumnHeader extends Header {
    constructor(props) {
        super(props);
        this.el = document.createElement("div");
    }
    
    /* @Override */
    render(props) {
        this.el.innerHTML = TUI_UTILS.NVL(String(props.columnInfo.header), "");
        let span = document.createElement("span");
        span.className = "blind";
        this.el.appendChild(span);
    }
}

/* Render Class - Column */
window["DeleteBtnColumn"] = class DeleteBtnColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("button");
        this.el.type = 'deleteBtn';
        this.el.className = 'delete';
        let callBack = TUI_UTILS.NVL(props.columnInfo.renderer.callBack, (e)=>{console.log(e)});
        this.el.addEventListener('click', function() {
            callBack(props);
        });
    }
}

window["DetailBtnColumn"] = class DetailBtnColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("button");
        this.el.type = 'detailBtn';
        this.el.className = 'detail';
        let callBack = TUI_UTILS.NVL(props.columnInfo.renderer.callBack, (e)=>{console.log(e)});
        this.el.addEventListener('click', function() {
            callBack(props);
        });
    }
}

window["OrderBtnColumn"] = class OrderBtnColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("button");
        this.el.type = 'orderBtn';
        this.el.className = 'order';
        let callBack = TUI_UTILS.NVL(props.columnInfo.renderer.callBack, (e)=>{console.log(e)});
        this.el.addEventListener('click', function() {
            callBack(props);
        });
    }
}

window["CustomColumn"] = class CustomColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("div");
        this.el.style["text-align"] = TUI_UTILS.NVL(props.columnInfo.align, "left");
        if(!TUI_UTILS.isEmpty(props.columnInfo.renderer?.options?.style)) {
            let style = props.columnInfo.renderer?.options?.style;
            for(let name in style) {
                this.el.style[name] = style[name];
            }
        }
        this.el.className = TUI_UTILS.NVL(props.columnInfo.renderer?.options?.className, this.el.className);
        
        let style = TUI_UTILS.NVL(props.columnInfo.renderer?.options?.styleCallback, (rowKey, columnName, value)=>this.el.style)(props.rowKey, props.columnInfo.name, props.value)
        for(let name in style) {
            this.el.style[name] = style[name];
        }
        this.el.className = TUI_UTILS.NVL(props.columnInfo.renderer?.options?.classCallback, (rowKey, columnName, value)=>this.el.className)(props.rowKey, props.columnInfo.name, props.value)
        
        this.render(props);
    }
}

window["NumberColumn"] = class NumberColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("input");
        this.el.type = "text";
        this.el.setAttribute("maxlength", TUI_UTILS.NVL(props.columnInfo.renderer?.options?.maxlength, 10));
        this.el.setAttribute("inputmode", "numeric");
        this.render(props);
        this.el.readOnly = true;
        this.el.style["width"] = '100%';
        this.el.style["text-align"] = TUI_UTILS.NVL(props.columnInfo.align, "right");
    }
    
    /* @Override */
    render(props) {
        if(props.columnInfo.renderer?.options?.fixed) {
            let fixed = TUI_UTILS.NVL(props.columnInfo.renderer?.options?.fixed, 0);
            if(props.columnInfo.renderer?.options?.comma) {
                this.el.value = TUI_UTILS.comma(TUI_UTILS.formatD(TUI_UTILS.NVL(String(props.value), "").replace(/[^-\.0-9]/g, ""), fixed));
            } else {
                this.el.value = TUI_UTILS.formatD(TUI_UTILS.NVL(String(props.value), "").replace(/[^-\.0-9]/g, ""), fixed);
            }
        } else {
            if(props.columnInfo.renderer?.options?.comma) {
                this.el.value = TUI_UTILS.comma(TUI_UTILS.NVL(String(props.value), "").replace(/[^-\.0-9]/g, ""));
            } else {
                this.el.value = TUI_UTILS.NVL(String(props.value), "").replace(/[^-\.0-9]/g, "");
            }
        }
    }
}

window["YnCheckBoxColumn"] = class YnCheckBoxColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("input");
        this.el.type = "checkbox";
        this.render(props);
        this.el.addEventListener('click', function(e) {
            let value = e.target.checked ? "Y" : "N";
            props.grid.setValue(props.rowKey, props.columnInfo.name, value);
        });
    }
    
    /* @Override */
    render(props) {
        this.el.checked = (props.value === "Y");
    }
}

window["PostEditorColumn"] = class PostEditorColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("input");
        this.el.type = "text";
        this.render(props);
        this.el.readOnly = true;
        this.el.style["width"] = '100%';
        this.el.style["text-align"] = TUI_UTILS.NVL(props.columnInfo.align, "left");
        this.el.addEventListener('dblclick', function(e) {
            new daum.Postcode({
                oncomplete: function(data) {
                    for(let key in data) {
                        if(!TUI_UTILS.isEmpty(props.columnInfo.renderer.options[key])) {
                            if(key === "zonecode") {
                                props.grid.setValue(props.rowKey, props.columnInfo.renderer.options[key], data[key].replace(/^(\d{3})(\d{2})$/, `$1-$2`));
                            } else {
                                props.grid.setValue(props.rowKey, props.columnInfo.renderer.options[key], data[key]);
                            }
                        }
                    }
                }
            }).open();
        });
    }
    
    /* @Override */
    render(props) {
        this.el.value = TUI_UTILS.NVL(String(props.value), "");
    }
}

window["TableColumn"] = class TableColumn extends Column {
    constructor(props) {
        super(props);
        this.el = document.createElement("table");
        this.el.style["width"] = '100%';
        this.el.className = "tui-grid-table";
        
        this.tagMap = {};
        
        let colgroup = document.createElement("colgroup");
        for(let option of props.columnInfo.renderer.colgroup) {
            let col = document.createElement("col");
            col["width"] = option["width"];
            colgroup.appendChild(col);
        }
        this.el.appendChild(colgroup);
        for(let row of props.columnInfo.renderer.table) {
            let tr = document.createElement("tr");
            tr.className = "tui-grid-row-odd tui-grid-cell-current-row";
            for(let col of row) {
                let td = document.createElement("td");
                td.className = "tui-grid-cell tui-grid-cell-has-input";
                td.colSpan = TUI_UTILS.NVL(col["colspan"], 1);
                td.rowSpan = TUI_UTILS.NVL(col["rowspan"], 1);
                td.name = col["name"];
                for(let name in col.style) {
                    td.style[name] = col.style[name];
                }
                this.tagMap[col["name"]] = {};
                this.tagMap[col["name"]]["formatter"] = TUI_UTILS.NVL(td?.formatter, (d)=>d);
                tr.appendChild(td);
            }
            this.el.appendChild(tr);
        }
        this.render(props);
    }
    
    /* @Override */
    render(props) {
        let {grid, rowKey} = props;
        for(let td of this.el.querySelectorAll("td")) {
            td.innerHTML = this.tagMap[td.name]["formatter"](grid.getValue(rowKey, td.name));
        }
    }
}

/* Editor Class */
window["EmailEditor"] = class EmailEditor extends Editor {
    constructor(props) {
        super(props);
        this.el = document.createElement("input");
        this.el.id = "grid_email_input"
        this.el.type = "text";
        this.render(props);
        
        let emailListener = function(e) {
            let {rowKey, columnName, value, instance} = e;
            if(columnName !== props.columnInfo.name || rowKey !== props.rowKey || TUI_UTILS.isEmpty(value)) return false;
            
            var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
            if(!regEmail.test(value)) {
                setTimeout(function() {
                    instance.startEditing(rowKey, columnName);
                    if(!TUI_UTILS.isEmpty(Tooltip)) {
                        Tooltip.create(document.getElementById('grid_email_input'), {
                            // 'top', 'bottom', 'left', 'right'
                            position    : ((rowKey === 0 || rowKey === 1) ? 'top' : 'auto'),
                            // CSS class: 'dark', 'error'
                            class       : 'error',
                            // 'top', 'bottom', 'left', 'right'
                            orientation : ((rowKey === 0 || rowKey === 1) ? 'bottom' : 'top'),
                            // offset in pixels
                            offset      : 0,
                            // trigger event: 'load', 'manual', 'hover', 'click', ...
                            showOn      : 'load',
                            // custom close icon
                            closeIcon   : false,
                            // default tooltip text
                            text        : '잘못된 이메일 형식입니다.(someone@example.com)'
                        });
                        setTimeout(function() {
                            if(document.activeElement !== document.getElementById('grid_email_input')) {
                                document.getElementById('grid_email_input')?.focus();
                            }
                        }, 300);
                    }
                }, 100);
            }
        }
        props.grid.emailListener = emailListener;
    }
}

window["NumberEditor"] = class NumberEditor extends Editor {
    constructor(props) {
        super(props);
        this.el = document.createElement("input");
        this.el.type = "text";
        this.el.setAttribute("maxlength", TUI_UTILS.NVL(props.columnInfo.editor?.options?.maxlength, 10));
        this.el.setAttribute("inputmode", "numeric");
        this.render(props);
        this.el.addEventListener('keyup', function(e) {
            let inputText = e.target.value;
            e.target.value = inputText.replace(/[^-\.0-9]/g, "");
        });
    }
}

window["PhoneEditor"] = class PhoneEditor extends Editor {
    constructor(props) {
        super(props);
        this.el = document.createElement("input");
        this.el.type = "text";
        this.el.pattern = "[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}"
        this.el.setAttribute("maxlength", 13);
        this.el.setAttribute("inputmode", "numeric");
        this.render(props);
        this.el.addEventListener('keyup', function(e) {
            let inputText = e.target.value;
            e.target.value = inputText.replace(/[^0-9]/g, "")
                                      .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`)
                                      .replace("--", "-");
        });
    }
}
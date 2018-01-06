/*!
 * 新增 DWZ suggest 的功能
 */

/* ========================================================================
 * B-JUI: zhilian-suggest.js  v1.0
 * @author CXF
 * ======================================================================== */
var DWZ={
	keyCode : {ENTER : 13,ESC : 27,END : 35,HOME : 36,SHIFT : 16,TAB : 9,LEFT : 37,RIGHT : 39,UP : 38,DOWN : 40,DELETE : 46,BACKSPACE : 8},
	eventType : {pageClear : "pageClear",resizeGrid : "resizeGrid"},
	isOverAxis : function(x, reference, size) {return (x > reference) && (x < (reference + size));},
	isOver : function(y, x, top, left, height, width) {return this.isOverAxis(y, top, height)&& this.isOverAxis(x, left, width);},
	pageInfo : {pageNum : "pageNum",numPerPage : "numPerPage",orderField : "orderField",orderDirection : "orderDirection"},
	statusCode : {ok : 200,error : 300,timeout : 301,warn : 250,info : 520},
	ui : {sbar : true},
	frag : {},
	_msg : {},
	_set : {loginUrl : "",loginTitle : "",debug : false},
	msg : function(key, args) {
		var _format = function(str, args) {
			args = args || [];
			var result = str || "";
			for ( var i = 0; i < args.length; i++) {
				result = result.replace(new RegExp("\\{" + i + "\\}", "g"),args[i]);
			}return result;
		};
		return _format(this._msg[key], args);},
	debug : function(msg) {if (this._set.debug) {if (typeof (console) != "undefined"){console.log(msg);}else{alert(msg);}}},
	loadLogin : function() {
		if ($.pdialog && DWZ._set.loginTitle) {
			$.pdialog.open(DWZ._set.loginUrl, "login", DWZ._set.loginTitle, {
				mask : true,
				width : 520,
				height : 260
			});
		} else {window.location = DWZ._set.loginUrl;}
	},
	obj2str : function(o) {
		var r = [];
		if (typeof o == "string")
			return "\""+ o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t")+ "\"";
		if (typeof o == "object") {
			if (!o.sort) {
				for ( var i in o)
					r.push(i + ":" + DWZ.obj2str(o[i]));
				if (!!document.all&& !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
					r.push("toString:" + o.toString.toString());
				}
				r = "{" + r.join() + "}";
			} else {
				for ( var i = 0; i < o.length; i++) {
					r.push(DWZ.obj2str(o[i]));
				}
				r = "[" + r.join() + "]";
			}
			return r;
		}
		return o.toString();
	},
	jsonEval : function(data) {try {if ($.type(data) == 'string'){return eval('(' + data + ')');}else{return data;}} catch (e) {return {};}},
	ajaxError : function(xhr, ajaxOptions, thrownError) {
		if (alertMsg) {
			alertMsg.error("<div>Http status: " + xhr.status + " " + xhr.statusText + "</div>" + "<div>ajaxOptions: " + ajaxOptions + "</div>" + "<div>thrownError: " + thrownError + "</div>" + "<div>" + xhr.responseText + "</div>");
		} else {
			alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:" + thrownError + "\n" + xhr.responseText);
		}
	},
	ajaxDone : function(json) {
		if (json.statusCode === undefined && json.message === undefined) {if (alertMsg){return alertMsg.error(json);}else{return alert(json);}}
		if (json.statusCode == DWZ.statusCode.error) {if (json.message && alertMsg){alertMsg.error(json.message);}
		} else if (json.statusCode == DWZ.statusCode.timeout) {if (alertMsg){alertMsg.error(json.message || DWZ.msg("sessionTimout"), {okCall : DWZ.loadLogin});}else{DWZ.loadLogin();}
		} else if(json.statusCode == DWZ.statusCode.warn){if (json.message && alertMsg){alertMsg.warn(json.message);}
		} else if(json.statusCode == DWZ.statusCode.info){if (json.message && alertMsg){alertMsg.info(json.message);}
		} else {if (json.message && alertMsg){alertMsg.correct(json.message);}};},
	init : function(pageFrag, options) {
		var op = $.extend({loginUrl : "login.html",loginTitle : null,callback : null,debug : false,statusCode : {}}, options);
		this._set.loginUrl = op.loginUrl;
		this._set.loginTitle = op.loginTitle;
		this._set.debug =op.debug;
		$.extend(DWZ.statusCode,op.statusCode);
		$.extend(DWZ.pageInfo,op.pageInfo);
		jQuery.ajax({
					type : 'GET',
					url : pageFrag,
					dataType : 'xml',
					timeout : 50000,
					cache : false,
					error : function(xhr) {alert('Error loading XML document: ' + pageFrag + "\nHttp status: " + xhr.status + " " + xhr.statusText);},
					success : function(xml) {
						$(xml).find("_PAGE_").each(function() {
							var pageId = $(this).attr("id");
							if (pageId)
								DWZ.frag[pageId] = $(this).text();
						});
						$(xml).find("_MSG_").each(function() {
							var id = $(this).attr("id");
							if (id){DWZ._msg[id] = $(this).text();}
						});
						if (jQuery.isFunction(op.callback))op.callback();}
				});
		var _doc = $(document);
		if (!_doc.isBind(DWZ.eventType.pageClear)) {
			_doc.bind(DWZ.eventType.pageClear, function(event) {
				var box = event.target;
				if ($.fn.xheditor) {
					$("textarea.editor", box).xheditor(false);
				}
			});
		}
	}
};

(function($) {
	
	var _lookup = {
			currentGroup : "",
			suffix : "",
			$target : null,
			pk : "id"
		};
	var _util = {
		_lookupPrefix : function(key) {
			var strDot = _lookup.currentGroup ? "." : "";
			return _lookup.currentGroup + strDot + key + _lookup.suffix;
		},
		lookupPk : function(key) {
			return this._lookupPrefix(key);
		},
		lookupField : function(key) {
			return this.lookupPk(key);
		}
	};
	$.extend({bringBackSuggest : function(args) {
		
		var $box = _lookup['$target'].parents(".unitBox:first");
		$box.find(":input").each(
				function() {
					
					var $input = $(this), inputName = $input.attr("name");
					for ( var key in args) {
						var name = (_lookup.pk == key) ? _util.lookupPk(key) : _util.lookupField(key);
						
						if (name == inputName) {
							$input.val(args[key]);
							break;
						}
					}
				});
	}});
	$.fn.extend({
		
		hoverClass : function(className, speed) {
			var _className = className || "hover";
			return this.each(function() {
				var $this = $(this), mouseOutTimer;
				$this.hover(
					function() {
						if (mouseOutTimer)
							clearTimeout(mouseOutTimer);
						$this.addClass(_className);
					},
					function() {
						mouseOutTimer = setTimeout(function() {
							$this.removeClass(_className);
						}, speed || 10);
					}
				);
			});
		},
		focusClass : function(className) {
			var _className = className || "textInputFocus";
			return this.each(function() {
				$(this).focus(function() {
					$(this).addClass(_className);
				}).blur(function() {
					$(this).removeClass(_className);
				});
			});
		},
		suggest : function() {
			var op = {
					suggest$ : "#suggest",
					suggestShadow$ : "#suggestShadow"
				};
				var selectedIndex = -1;
				
				return this.each(function() {
							var $input = $(this).attr('autocomplete', 'off').keydown(
							function(event) {
								if (event.keyCode == DWZ.keyCode.ENTER&& $(op.suggest$).is(':visible'))
									return false;
							});
							var suggestFields = $input.attr('suggestFields').split(",");
							
							function _show(event) {
								
								var offset = $input.offset();
								var iTop = offset.top + this.offsetHeight;
								var iWidth = $input.width()+12+'px';
								var $suggest = $(op.suggest$);
								if ($suggest.size() == 0)
									$suggest = $('<div id="suggest"></div>').appendTo($('body'));
								$suggest.css({
									left : offset.left + 'px',
									top : iTop + 'px'
								}).show();
								
								_lookup = $.extend(_lookup,{
									currentGroup : $input.attr("lookupGroup") || "",
									suffix : $input.attr("suffix") || "",
									$target : $input,
									pk : $input.attr("lookupPk") || "id"
								});
								
								var url = unescape($input.attr("suggestUrl")).replaceTmById($(event.target).parents(".unitBox:first"));
								if (!url.isFinishedTm()) {
									alertMsg.error($input.attr("warn") || DWZ.msg("alertSelectMsg"));
									return false;
								}
								var postData ={};
								if ($input.attr("catchFields") != null) {
									var catchField = $input.attr("catchFields").split(",");
									var catchValue = "";
									for (var i=0; i<catchField.length; i++) {
										if ($("#"+catchField[i]).val() != "")
											catchValue += "," + $("#"+catchField[i]).val();
									}
									if (catchValue.length > 0)
										catchValue = catchValue.substring(1);
									postData["catchValue"]=catchValue;
								}
								postData[$input.attr("postField")||"inputValue"]=$input.val();
								
								$.ajax({
										global : false,
										type : 'POST',
										dataType : "json",
										url : url,
										cache : false,
										data : postData,
										success : function(response) {
											
											if (!response)
												return;
											var html = '';
											$.each(response,
												function(i) {
													var liAttr = '', liLabel = '';
													for ( var i = 0; i < suggestFields.length; i++) {
														var str = this[suggestFields[i]];
														if (str) {
															if (liLabel)
																liLabel += '-';
															liLabel += str;
														}
													}
													for ( var key in this) {
														if (liAttr)
															liAttr += ',';
														liAttr += key + ":'" + this[key] + "'";
													}
													html += '<li lookupAttrs="' + liAttr + '">' + liLabel + '</li>';
												});
											
											var $lis = $suggest.html('<ul style="width:'+iWidth+'" >' + html + '</ul>').find("li");
											
											$lis.hoverClass("selected").click(
															function() {
																
																_select($(this));
															});
											
											if ($lis.size() == 1 && event.keyCode != DWZ.keyCode.BACKSPACE) {
												_select($lis.eq(0));
											} else if($lis.size() == 0){
												
												var jsonStr = "";
												for ( var i = 0; i < suggestFields.length; i++) {
													if (_util.lookupField(suggestFields[i]) == event.target.name) {
														break;
													}
													if (jsonStr)
														jsonStr += ',';
													jsonStr += suggestFields[i] + ":''";
												}
												jsonStr = "{" + jsonStr + "}";
												
												$.bringBackSuggest(DWZ.jsonEval(jsonStr));
												
											}
										},
										error : function() {
											$suggest.html('');
										}
									});
								$(document).bind("click", _close);
								return false;
							}
							function _select($item) {
								var jsonStr = "{" + $item.attr('lookupAttrs') + "}";
								$.bringBackSuggest(DWZ.jsonEval(jsonStr));
							}
							function _close() {
								$(op.suggest$).html('').hide();
								selectedIndex = -1;
								$(document).unbind("click", _close);
							}
							$input.focus(_show).click(false).keyup(
									function(event) {
										var $items = $(op.suggest$).find("li");
										switch (event.keyCode) {
										case DWZ.keyCode.ESC:
										case DWZ.keyCode.TAB:
										case DWZ.keyCode.SHIFT:
										case DWZ.keyCode.HOME:
										case DWZ.keyCode.END:
										case DWZ.keyCode.LEFT:
										case DWZ.keyCode.RIGHT:
											break;
										case DWZ.keyCode.ENTER:
											_close();
											break;
										case DWZ.keyCode.DOWN:
											if (selectedIndex >= $items.size() - 1)
												selectedIndex = -1;
											else
												selectedIndex++;
											break;
										case DWZ.keyCode.UP:
											if (selectedIndex < 0)
												selectedIndex = $items.size() - 1;
											else
												selectedIndex--;
											break;
										default:
											_show(event);
										}
										$items.removeClass("selected");
										if (selectedIndex >= 0) {
											var $item = $items.eq(selectedIndex).addClass("selected");
											_select($item);
										}
									});
						});
			}
	
	});
	$.checkbox = {
		selectAll : function(_name, _parent) {
			this.select(_name, "all", _parent);
		},
		unSelectAll : function(_name, _parent) {
			this.select(_name, "none", _parent);
		},
		selectInvert : function(_name, _parent) {
			this.select(_name, "invert", _parent);
		},
		select : function(_name, _type, _parent) {
			$parent = $(_parent || document);
			$checkboxLi = $parent.find(":checkbox[name='" + _name + "']");
			switch (_type) {
			case "invert":
				$checkboxLi.each(function() {
					$checkbox = $(this);
					$checkbox.attr('checked', !$checkbox.is(":checked"));
				});
				break;
			case "none":
				$checkboxLi.attr('checked', false);
				break;
			default:
				$checkboxLi.attr('checked', true);
				break;
			}
		}
	};
    $(document).on(BJUI.eventType.initUI, function(e) {
    	 if ($.fn.suggest){
         	$("input[suggestFields]", document).suggest();
         }
    })
})(jQuery);
$(function() {

	$(".resultView").draggable({
		cursor : "move",
		handle : ".window",
		helper : "#menu",
		revert : "invalid",
	});

	$(".iframe").resize(function() {
		$($(this).parent()).width($(this).width() + 60);
		$($(this).parent()).height($(this).height() + 20);
	});

	$(".resultView .window .closeWindow").mousedown(function() {
		$(this).parent().parent().remove();
	})

	$(".resultView .window .refreshWindow").mousedown(function() {
		var iframe = $(this).parent().parent().find("iframe");
		$(iframe).attr("src", $(iframe).attr("src"));
	})

});

// Jquery resize支持DIV
(function($, h, c) {
	var a = $([]), e = $.resize = $.extend($.resize, {}), i, k = "setTimeout", j = "resize", d = j
			+ "-special-event", b = "delay", f = "throttleWindow";
	e[b] = 250;
	e[f] = true;
	$.event.special[j] = {
		setup : function() {
			if (!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.add(l);
			$.data(this, d, {
				w : l.width(),
				h : l.height()
			});
			if (a.length === 1) {
				g()
			}
		},
		teardown : function() {
			if (!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.not(l);
			l.removeData(d);
			if (!a.length) {
				clearTimeout(i)
			}
		},
		add : function(l) {
			if (!e[f] && this[k]) {
				return false
			}
			var n;
			function m(s, o, p) {
				var q = $(this), r = $.data(this, d);
				r.w = o !== c ? o : q.width();
				r.h = p !== c ? p : q.height();
				n.apply(this, arguments)
			}
			if ($.isFunction(l)) {
				n = l;
				return m
			} else {
				n = l.handler;
				l.handler = m
			}
		}
	};
	function g() {
		i = h[k](function() {
			a.each(function() {
				var n = $(this), m = n.width(), l = n.height(), o = $.data(
						this, d);
				if (m !== o.w || l !== o.h) {
					n.trigger(j, [ o.w = m, o.h = l ])
				}
			});
			g()
		}, e[b])
	}
})(jQuery, this);

function newResultView(classname) {
	//alert(className);
	var resultView_width = 0.56 * $(window).width();
	var resultView_height = 500;
	// window没有跟着拉动变大

	//alert($("#NumericSelected").val());

	var div = '<div class="resultView" id="resultView" >'
			+ '<div class="window" >'
			+ '	<img src="./image/button-close.png" class="closeWindow">'
			+ '<img src="./image/button-refresh.png" class="refreshWindow">'
			+ '</div>' + '	<iframe class="iframe" src="./' + getResultViewURL(classname)
			+ '" scrolling="no"'
			+ '	frameBorder="no"  height="479px" width="'
			+ (resultView_width - 20) + 'px"> </iframe>' + '</div>';

	// var targetY = setResultViewWrapHeight();

	// var resultViewWrap_height = 0;
	// // var obj = null;
	// $(".resultView").each(function() {
	// var cur = $(this).offset().top + $(this).height();
	// if (cur > resultViewWrap_height) {
	// resultViewWrap_height = cur;
	// obj = $(this);
	// }
	// });

	// $(".resultViewWrap").height(resultViewWrap_height+10);
	$(".resultViewWrap").append(div);
	$(".resultView:last").width(resultView_width);
	// var lastObj =
	// $(".resultViewWrap:last").find("iframe");
	// lastObj.width(resultView_width);
	// alert(lastObj);
	// lastObj.style.top = obj.offset().top + obj.height();
	// alert(lastObj.offset().top);

	initResultViewUi();
}

function initResultViewUi() {

	$('.resultViewWrap').height($('.resultViewWrap').height() + 30);
	$(".resultView .window .closeWindow").mousedown(function() {
		$(this).parent().parent().remove();
	})

	$(".resultView .window .refreshWindow").mousedown(function() {
		var iframe = $(this).parent().parent().find("iframe");
		$(iframe).attr("src", $(iframe).attr("src"));
	})
	$(".resultView").draggable({
		cursor : "move",
		handle : ".window",
		helper : "#menu",
		revert : "invalid",
	});
	$(".iframe").resize(function() {
		$($(this).parent()).width($(this).width() + 50);
		$($(this).parent()).height($(this).height() + 50);
		// alert($(this).height());
	});

}
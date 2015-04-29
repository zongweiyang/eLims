		function ajaxList(than) {
			var data = null;
			$.ajax({
				url: than.attr('data'),
				type: "post",
				dataType: "json",
				async:false,
				success: function(result) {
					data = result;
				}
			});
			return data;
		}
		function createCombox(display){
			var than = $(this);
			var width = than.width();
			var ulTemplate = $("<ul>").addClass("select").width(width).css({
				'margin-top':than.height()+'px'
			});
			var liTemplate = $("<li>").hover(function(){
				$(this).closest(".select").children().removeClass("select_hover");
				$(this).addClass("select_hover");
			},function(){
				$(this).removeClass("select_hover");
			});
			
			var result = ajaxList(than);
			
			than.prevAll(".select").remove();
			if (result instanceof Array && result.length> 0) {   
				var ul = ulTemplate.clone();
				$.each(result,function(i,n) {
					var li = liTemplate.clone(true);
					li.html(n);
					ul.append(li);
				});
				than.before(ul);
				if (display==true){
					ul.show();
				}
			}
		}
		function choose(key) {
			var select = $(this).prevAll(".select").first();
			var boo = select.find("li").hasClass("select_hover");
			select.show();
			if (boo) {
				var lis = select.find("li");
				var li = select.find("li.select_hover");
				li.removeClass("select_hover");
				if (key == 38) {
					if (li.index() == 0) {
						lis.last().addClass("select_hover");
					} else {
						lis.eq(li.index()-1).addClass("select_hover");
					}
				} else if (key == 40) {
					if (li.index() == lis.length-1) {
						lis.first().addClass("select_hover");
					} else {
						lis.eq(li.index()+1).addClass("select_hover");
					}
				} else if (key == 13) {
					li.click();
				}
			} else {
				if (key == 38) {
					select.find("li:last").addClass("select_hover");
				} else if (key == 40) {
					select.find("li:first").addClass("select_hover");
				} 
			}
		}
		$(function(){
			$(".combox").each(function(){
				createCombox.call(this);
			});
			$(".combox").keyup(function(event){
				var keyCode = event.keyCode;
				var than = $(this);
				var inputTest = than.val().toLowerCase();
				if (keyCode < 37 || keyCode > 40 && keyCode != 13) {
					var lis = than.prevAll(".select").show().find("li");
					if (lis.length > 0) {
						lis.each(function(i,n){
							var text = $(this).html().toLowerCase();
							if (text.indexOf(inputTest) == -1) {
								$(this).hide();
							} else {
								$(this).show();
							}
						});
					}
				} else {
					choose.call(this,keyCode)
				}
			});
			//输入框有焦点
			$(".combox").focus(function(event){
				$(".select").hide();
				var select = $(this).prevAll(".select").first();
				var len = select.find("li").length;
				if (len) {
					select.show();
				}
			})
			$(".box").on("click",".select li",function(){
				var val = $(this).html();
				var select = $(this).parents(".select");
				select.nextAll(".combox").first().val(val);
				$(".select").hide();
			});
			$("body").click(function(event){
				var target = $(event.target);
				var b = target.hasClass("combox") || target.hasClass("select") || target.parents(".select").length > 0;
				if (!b) {
					$(".select").hide();
				}
			});
		});
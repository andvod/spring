<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <title>Slide Down Box Menu with jQuery and CSS3</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="description" content="Slide Down Box Menu with jQuery and CSS3" />
        <meta name="keywords" content="jquery, css3, sliding, box, menu, cube, navigation, portfolio, thumbnails"/>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
		
		<spring:url value="/assets/css/styleRoom.css" var="mainCss" />
		<spring:url value="/assets/js/jqueryRoom.js" var="jqueryJs" />

		<link href="${mainCss}" rel="stylesheet" />

		<spring:url value="/assets/images" var="images" />
        <style>
			body{
				background:#333 url(${images}/bg.jpg) repeat top left;
				font-family:Arial;
			}
			span.reference{
				position:fixed;
				left:10px;
				bottom:10px;
				font-size:12px;
			}
			
			span.reference a{
				color:#aaa;
				text-transform:uppercase;
				text-decoration:none;
				text-shadow:1px 1px 1px #000;
				margin-right:30px;
			}
			span.reference a:hover{
				color:#ddd;
			}
			ul.sdt_menu{
				margin-top:120px;
			}
			ul.sdt_menu_cars{
				margin-top:320px;
			}
			h1.title{
				text-indent:-9000px;
				background:transparent url(${images}/title.png) no-repeat top left;
				width:633px;
				height:71px;
			}
		</style>
    </head>

    <body>
		<div class="content">
			<h1 class="title">Auto Forum</h1>
			<ul id="sdt_menu" class="sdt_menu">
				<li>
					<a href="#">
						<img src="${images}/2.jpg" alt=""/>
						<span class="sdt_active">
							<a href="http://localhost:8080/com.society/bmw"></a>
						</span>
						<span class="sdt_wrap">
							<span class="sdt_link">BMW</span>
							<span class="sdt_descr">BMW samochody</span>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="${images}/1.jpg" alt=""/>
						<span class="sdt_active">
							<a href="http://localhost:8080/com.society/audi"></a>
						</span>
						<span class="sdt_wrap">
							<span class="sdt_link">Audi</span>
							<span class="sdt_descr">Audi samochody</span>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="${images}/3.jpg" alt=""/>
						<span class="sdt_active">
							<a href="http://localhost:8080/com.society/mercedes"></a>
						</span>
						<span class="sdt_wrap">
							<span class="sdt_link">Mercedes</span>
							<span class="sdt_descr">Mercedes samochody</span>
						</span>
					</a>
				</li>
			</ul>
			<ul id="sdt_menu" class="sdt_menu_cars">
				<li>
					<a href="#">
						<img src="${images}/4.jpg" alt=""/>
						<span class="sdt_active">
							<a href="http://localhost:8080/com.society/bmwelements"></a>
						</span>
						<span class="sdt_wrap">
							<span class="sdt_link">BMW</span>
							<span class="sdt_descr">BMW czesci samochodowe</span>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="${images}/5.jpg" alt=""/>
						<span class="sdt_active">
							<a href="http://localhost:8080/com.society/audielements"></a>
						</span>
						<span class="sdt_wrap">
							<span class="sdt_link">Audi</span>
							<span class="sdt_descr">Audi czesci samochodowe</span>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="${images}/6.jpg" alt=""/>
						<span class="sdt_active">
							<a href="http://localhost:8080/com.society/mercedeselements"></a>
						</span>
						<span class="sdt_wrap">
							<span class="sdt_link">Mercedes</span>
							<span class="sdt_descr">Mercedes czesci samochodowe</span>
						</span>
					</a>
				</li>
			</ul>
		</div>
        <div>
            <span class="reference">
                <a href="https://www.facebook.com">facebook</a>
			</span>
		</div>

        <!-- The JavaScript -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
		<script type="text/javascript" src="${jqueryJs}"></script>
        <script type="text/javascript">
            $(function() {
				/**
				* for each menu element, on mouseenter, 
				* we enlarge the image, and show both sdt_active span and 
				* sdt_wrap span. If the element has a sub menu (sdt_box),
				* then we slide it - if the element is the last one in the menu
				* we slide it to the left, otherwise to the right
				*/
                $('#sdt_menu > li').bind('mouseenter',function(){
					var $elem = $(this);
					$elem.find('img')
						 .stop(true)
						 .animate({
							'width':'170px',
							'height':'170px',
							'left':'0px'
						 },400,'easeOutBack')
						 .andSelf()
						 .find('.sdt_wrap')
					     .stop(true)
						 .animate({'top':'140px'},500,'easeOutBack')
						 .andSelf()
						 .find('.sdt_active')
					     .stop(true)
						 .animate({'height':'170px'},300,function(){
						var $sub_menu = $elem.find('.sdt_box');
						if($sub_menu.length){
							var left = '170px';
							if($elem.parent().children().length == $elem.index()+1)
								left = '-170px';
							$sub_menu.show().animate({'left':left},200);
						}	
					});
				}).bind('mouseleave',function(){
					var $elem = $(this);
					var $sub_menu = $elem.find('.sdt_box');
					if($sub_menu.length)
						$sub_menu.hide().css('left','0px');
					
					$elem.find('.sdt_active')
						 .stop(true)
						 .animate({'height':'0px'},300)
						 .andSelf().find('img')
						 .stop(true)
						 .animate({
							'width':'0px',
							'height':'0px',
							'left':'85px'},400)
						 .andSelf()
						 .find('.sdt_wrap')
						 .stop(true)
						 .animate({'top':'25px'},500);
				});
            });
            
        </script>
        
    </body>
</html>
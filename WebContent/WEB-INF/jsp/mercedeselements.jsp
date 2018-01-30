<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<spring:url value="/assets/css/styleWebsocket.css" var="mainCss" />
		<spring:url value="/assets/js/websocket.js" var="jqueryJs" />
		<spring:url value="/assets/images" var="images" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<link href="${mainCss}" rel="stylesheet" />
    	<link href="${mainJs}" rel="stylesheet" />
    
</head>
<body style="background:url(${images}/bg.jpg) repeat top left;">
	<div class="container">
    <div class="row chat-window col-md-8 col-md-7" id="chat_window_1" style="margin-left:10px;">
        <div class="col-xs-12 col-md-12">
        	<div class="panel panel-default">
                <div class="panel-heading top-bar">
                    <div class="col-md-8 col-xs-8">
                        <h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span> Chat - ${name}</h3>
                    </div>
                    <div class="col-md-4 col-xs-4" style="text-align: right;">
                        <a href="http://192.168.200.83:8080/com.society/room"><span class="glyphicon glyphicon-remove icon_close" data-id="chat_window_1"></span></a>
                    </div>
                </div>
                <div id="messages" class="panel-body msg_container_base">
                    
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="userinput" type="text" class="form-control input-sm chat_input" placeholder="Write your message here..." />
                        <span class="input-group-btn">
                        <button class="btn btn-primary btn-sm" onclick="start()" id="btn-chat">Wyslac</button>
                        </span>
                    </div>
                </div>
    		</div>
        </div>
    </div>
    
    
</div>
    
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		var webSocket = new WebSocket(
				'ws://192.168.200.83:8080/com.society/mercedeselements');
		
		var errorMessage = 'message';

		webSocket.onerror = function(event) {
			onError(event)
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};
		
		webSocket.onclose = function() {
			onClose()
		};
		
		function onMessage(event) {
			var currentdate = new Date();
			
			document.getElementById('messages').innerHTML += 
				'<div class="row msg_container base_receive">' +
            '<div class="col-md-10 col-xs-10">' +
                '<div class="messages msg_receive">' +
                 event.data +
                    '<br />' +
                    currentdate.getDay() + "/"+currentdate.getMonth() 
                    + "/" + currentdate.getFullYear() + " @ " 
                    + currentdate.getHours() + ":" 
                    + currentdate.getMinutes() + ":" + currentdate.getSeconds()
                '</div>' +
            '</div>' +
        '</div>';
					
		}
		
		function onClose() {
			alert('polaczenie zamkniete, zaloguj sie ponownie');
			window.location.replace("http://192.168.200.83:8080/com.society/login");
		}

		function onOpen(event) {
			document.getElementById('messages').innerHTML += 'Nawiazano polaczenie';
		}

		function onError(event) {
			document.getElementById('messages').innerHTML += '<br/>' + event.data;
			window.location.replace("http://google.com");
		}

		function start() {
			var text = document.getElementById("userinput").value;

			webSocket.send(text);
			return false;
		}
	</script>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>

<html>
<head>
	<title>War</title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
		
<div id="outerContainer" class="display-flex column-container">

	<div id="topContainer" class="display-flex transparent row-container vh30">
	
 		<div id="player-container" class="innerContainer display-flex">
		
			<div id="player-label-container" class="flex-column-evenly">
				<div class="player-label">PLAYER 2</div>
				<div class="player-score-container flex-column-evenly center-center">
					
					${turn.player2Score}		
				</div>
			</div>
			
			
		
			<div class="player-decks-container">
				<div id="player-two-win-deck" class="player-deck display-flex center-center">
					<div class="card-value display-flex center-center">${turn.player2GameDeck}</div>
				</div>
				
				<div id="player-two-game-deck" class="player-deck display-flex center-center">
					<div class="card-value display-flex center-center">${turn.player2WinDeck}</div>
				</div>				
			</div>
		
		</div>
		
		<div id="announcementsContainer" class="innerContainer flex-column-evenly">
		
			<div id="announcements">
			</div>
		
			<div id="gameStatus">
			</div>
		
		</div>
	</div>
	
	
	<div id="middleContainer" class="display-flex transparent row-container vh30">
	
		<div id="leftGameContainer" class="middle-inner-container middle-inner-container-left transparent">
		</div>
		
		<div id="centerGameContainer" class="middle-inner-container transparent flex-column-evenly">
			
			<div class="playing-half display-flex">
			
				<div id="top-playing-card-container">
					<div id="playing-card-one" class="playing-cards top-playing-cards display-flex center-center">
						<div class="card-value display-flex center-center">HK</div>
					</div>
					<div id="playing-card-two" class="playing-cards display-flex center-center">
						<div class="card-value display-flex center-center">HK</div>
					</div>
					<div id="playing-card-three" class="playing-cards display-flex center-center">
						<div class="card-value display-flex center-center">HK</div>
					</div>
				</div>
			
			</div>
			
			<div id="bottom-playing-half" class="playing-half display-flex">
			
				<div id="bottom-playing-card-container">
				
					<div id="playing-card-four" class="playing-cards bottom-playing-cards display-flex center-center">
						<div class="card-value display-flex center-center">HK</div>
					</div>
					<div id="playing-card-five" class="playing-cards display-flex center-center">
						<div class="card-value display-flex center-center">HK</div>
					</div>
					<div id="playing-card-six" class="playing-cards display-flex center-center">
						<div class="card-value display-flex center-center">HK</div>
					</div>
				
				</div>
			</div>
		</div>
		
		<div id="rightGameContainer" class="middle-inner-container middle-inner-container-right transparent">
		</div>
	</div>
	
	<div id="bottomContainer" class="display-flex transparent row-container vh30">
	
		<div id="gameControls" class="innerContainer flex-column-evenly">
			
			<div id="draw-button" class="display-flex center-center fs30">
			
				<form:form method="GET" modelAttribute="turn" action="nextTurn">
								
				
					<input type="submit" value="DRAW" />
					
				</form:form>
			</div>
			
			<div id="forfeit-button" class="display-flex center-center fs30">
				<!-- FOREFEIT -->
				<p>${turn.player1Score}</p>
			</div>
			
			<div id="back-button" class="display-flex center-center fs30">
				
				
				<form:form method="GET" modelAttribute="turn" action="getTurns">
				
				
					<input type="submit" value="See Turns" />
					
				</form:form>
			</div>
		</div>
		
		<div id="timeControls" class="innerContainer">
			<p>${turn}</p>
		</div>
		
		<div id="player-container" class="innerContainer display-flex">
		
			<div id="player-label-container" class="flex-column-evenly">
				<div class="player-label">PLAYER 1</div>
				<div class="player-score-container flex-column-evenly center-center">
					
					${turn.player1Score}

				</div>
			</div>
		
			<div class="player-decks-container">
				<div id="player-one-game-deck" class="player-deck display-flex center-center">
					<div class="card-value display-flex center-center">${turn.player1GameDeck}</div>
				</div>
				
				<div id="player-one-win-deck" class="player-deck display-flex center-center">
					<div class="card-value display-flex center-center">${turn.player1WinDeck}</div>
				</div>
			</div>
		
		</div>
		
	</div>
		
	</div>

</div>
	
</body>

</html>
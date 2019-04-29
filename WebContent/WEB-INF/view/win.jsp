<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>All Turns</title>
</head>

<body>

<h1>Congratulations ${game.winner}</h1>
<h2>It took ${turn.turnNumber} turns</h2>
<%-- <h3>This is turn ${turn}</h3> --%>


<a href="/war"><button>New Game</button></a>

<%--  <c:redirect url="/newGame"/>NEW GAME 2 --%>

<table border="1">
<thead>
	<tr>
		<td>turn Number</td>
		<td>Player1 Score</td>
		<td>Player2 Score</td>
		
		<td>Player1 Card</td>
		<td>Player2 Card</td>
		<td>Player1 2nd Card</td>
		<td>Player2 2nd Card</td>
		<td>Player1 3rd Card</td>
		<td>Player1 3rd Card</td>
		
		<td>Player1 Game Deck</td>
		<td>Player1 Win Deck</td>
		<td>Player2 Game Deck</td>
		<td>Player2 Win Deck</td>
		<td>Winner</td>
	</tr>
</thead>

<tbody>
	<c:forEach items="${allTurns}" var="thisTurn">
	<tr>
		<td>${thisTurn.turnNumber}</td>
		<td>${thisTurn.player1Score}</td>
		<td>${thisTurn.player2Score}</td>
		
		<td>${thisTurn.player1Card}</td>
		<td>${thisTurn.player2Card}</td>
		<td>${thisTurn.secondPlayer1Card}</td>
		<td>${thisTurn.secondPlayer2Card}</td>
		<td>${thisTurn.thirdPlayer2Card}</td>
		<td>${thisTurn.thirdPlayer1Card}</td>
		
		<td>${thisTurn.player1GameDeck}</td>
		<td>${thisTurn.player1WinDeck}</td>
		<td>${thisTurn.player2GameDeck}</td>
		<td>${thisTurn.player2WinDeck}</td>
		<td>${game.winner}</td>
	</tr>
	</c:forEach>

</tbody>
</table>


</body>
</html>
	

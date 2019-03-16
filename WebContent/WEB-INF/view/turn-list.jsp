<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

	<title>Turn List</title>

</head>
<body>
		
	<table>
		
		<tr>
		
			<th>Turn Number</th>
			<th></th>
			<th>Player1 Score</th>
			<th>Player1 Game Deck</th>
			<th>Player1 Win Deck</th>
			<th></th>
			<th>Player2 Score</th>
			<th>Player2 Game Deck</th>
			<th>Player2 Win Deck</th>
			
		</tr>
	
		<c:forEach var="tempTurn" items="${turns}">
		
			<tr>
				<td>${tempTurn.id}</td>
				<td></td>
				<td>${tempTurn.player1Score}</td>
				<td>${tempTurn.player1GameDeck}</td>
				<td>${tempTurn.player1WinDeck}</td>
				<td></td>
				<td>${tempTurn.player2Score}</td>
				<td>${tempTurn.player2GameDeck}</td>
				<td>${tempTurn.player2WinDeck}</td>
			</tr>
		
		</c:forEach>
	</table>

</body>

</html>

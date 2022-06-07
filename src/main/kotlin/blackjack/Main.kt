package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.game.BlackJackGame
import blackjack.view.TakeMore
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    val inputView = InputView()
    val resultView = ResultView()
    val playerNames = inputView.enterPlayerName()
    val cardDeck = CardDeck()
    val takeMore = TakeMore()
    val players = Players(playerNames)

    val blackJackGame = BlackJackGame(cardDeck, players, takeMore)
    resultView.printInitDistributed(blackJackGame.players)

    blackJackGame.playersToPlay()
        .map {
            while (it.canMoreGame() && blackJackGame.wantToTake(it)) {
                blackJackGame.moreGamesByPlayer(it)
                resultView.printCardsByPlayer(it, false)
            }

            resultView.printCardsByPlayer(it, false)
        }

    resultView.printCardsByPlayers(blackJackGame.players, true)
}

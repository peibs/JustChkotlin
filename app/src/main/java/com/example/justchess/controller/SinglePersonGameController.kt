package com.example.justchess.controller

import com.example.justchess.Bot
import com.example.justchess.engine.Game
import com.example.justchess.engine.Move

/**
 * controller for a single person game against a bot
 */
class SinglePersonGameController(
    game: Game,
    private val bot: Bot
) : BaseGameController(game) {
    override fun postPlayerMoves() {
        super.postPlayerMoves()
        if (game.playerTurn() == bot.playerId) {
            executePlayerMoves(
                bot.getNextTurn(game.getCurrentBoard())
            )
            promotePieceIfNeeded()
        }
    }

    private fun promotePieceIfNeeded() {
        val promotableCoordinate = game.getPromotablePawnCoordinate()
        if (promotableCoordinate != null) {
            val piece = bot.getPromotedPiece(promotableCoordinate, game.getCurrentBoard())
            this.promotePawn(piece)
        }
    }
}
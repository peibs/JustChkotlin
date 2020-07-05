package com.example.justchess

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.justchess.engine.*
import com.example.justchess.engine.factory.DefaultGameFactory

abstract class ChessGameActivity : AppCompatActivity() {
    private var gameFactory: GameFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeGameFactory()

        val chessView = ChessBoardView(this)

        val controller = createController()
        chessView.addViewListener(object : ChessBoardViewListener {
            override fun onCoordinateSelected(coordinate: Coordinate) {
                val playerTurn = controller.game.playerTurn()
                controller.selectCoordinate(coordinate)
                val viewModel = controller.getViewModel()
                chessView.setViewModel(
                    viewModel
                )
                val promotablePawnCoordinate = controller.game.getPromotablePawnCoordinate()
                val playerTurnUnchanged = controller.game.playerTurn() == playerTurn
                if (promotablePawnCoordinate != null && playerTurnUnchanged) {
                    launchPawnPromotionDialog(controller, chessView, promotablePawnCoordinate)
                }
            }
        })
        chessView.setViewModel(controller.getViewModel())
        setContentView(chessView)
    }

    private fun initializeGameFactory() {
        val blackImageProvider = PieceImageProvider(
            BitmapFactory.decodeResource(resources, R.drawable.black_bishop),
            BitmapFactory.decodeResource(resources, R.drawable.black_king),
            BitmapFactory.decodeResource(resources, R.drawable.black_knight),
            BitmapFactory.decodeResource(resources, R.drawable.black_pawn),
            BitmapFactory.decodeResource(resources, R.drawable.black_queen),
            BitmapFactory.decodeResource(resources, R.drawable.black_rook)
        )
        val whiteImageProvider = PieceImageProvider(
            BitmapFactory.decodeResource(resources, R.drawable.white_bishop),
            BitmapFactory.decodeResource(resources, R.drawable.white_king),
            BitmapFactory.decodeResource(resources, R.drawable.white_knight),
            BitmapFactory.decodeResource(resources, R.drawable.white_pawn),
            BitmapFactory.decodeResource(resources, R.drawable.white_queen),
            BitmapFactory.decodeResource(resources, R.drawable.white_rook)
        )
        gameFactory = DefaultGameFactory(
            whiteImageProvider,
            blackImageProvider
        )
    }

    protected fun getGameFactory(): GameFactory {
        return gameFactory!!
    }

    private fun createController(): GameController {
        return buildController(getGameFactory().createNewGame())
    }

    protected abstract fun buildController(game: Game): GameController

    private fun launchPawnPromotionDialog(
        controller: GameController,
        chessBoardView: ChessBoardView,
        coordinate: Coordinate
    ) {
        AlertDialog.Builder(this)
            .setTitle(R.string.promote_pawn)
            .setItems(
                R.array.promotion_options
            ) { _, which ->
                val pieceFactory = getPieceFactory(controller.game.playerTurn())
                val piece = pieceFactory.createPromotedPiece(coordinate, which)
                controller.promotePawn(piece)
                chessBoardView.setViewModel(
                    controller.getViewModel()
                )
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun getPieceFactory(playerId: Int): PieceFactory {
        return if (playerId == 0) {
            getGameFactory().whitePieceFactory
        } else {
            getGameFactory().blackPieceFactory
        }
    }
}
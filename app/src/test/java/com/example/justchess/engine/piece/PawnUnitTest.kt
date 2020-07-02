package com.example.justchess.engine.piece

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.mocks.FakeBoard
import org.junit.Assert.fail
import org.junit.Test

class PawnUnitTest {

    @Test
    fun can_create_pawn() {
        try {
            Pawn(
                Coordinate(0, 1),
                0,
                null,
                true
            )
        } catch (e: Throwable) {
            fail("Failed to instantiate pawn")
        }
    }

    @Test
    fun pawn_white_all_moves_valid() {
        val validMoves = Pawn(
            Coordinate(1, 1),
            0,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 3)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    0
                )
            )
        )
    }

    @Test
    fun pawn_white_all_moves_valid_first_move() {
        val validMoves = Pawn(
            Coordinate(1, 6),
            0,
            null,
            false
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 4)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    5
                )
            )
        )
    }

    @Test
    fun pawn_white_all_moves_valid_first_move_forward_obstructed() {
        val validMoves = Pawn(
            Coordinate(1, 6),
            0,
            null,
            false
        ).getValidMoves(
            FakeBoard(
                false,
                Pawn(Coordinate(1, 5), 0, null, true)
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    5
                )
            )
        )
    }

    @Test
    fun pawn_white_left_move_invalid() {
        val validMoves = Pawn(
            Coordinate(0, 1),
            0,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    0
                )
            )
        )
    }

    @Test
    fun pawn_white_right_move_invalid() {
        val validMoves = Pawn(
            Coordinate(7, 1),
            0,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    7,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    0
                )
            )
        )
    }

    @Test
    fun pawn_white_cannot_move_into_white_piece() {
        val validMoves = Pawn(
            Coordinate(4, 1),
            0,
            null,
            true
        ).getValidMoves(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 0),
                    0,
                    null,
                    true
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    3,
                    0
                )
            )
        )
        assert(
            !destinations.contains(
                Coordinate(
                    4,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    5,
                    0
                )
            )
        )
    }

    @Test
    fun pawn_cannot_move_if_king_will_be_checked_after() {
        val validMoves = Pawn(
            Coordinate(4, 1),
            0,
            null,
            true
        ).getValidMoves(FakeBoard(true))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }

    @Test
    fun pawn_black_all_moves_valid() {
        val validMoves = Pawn(
            Coordinate(1, 1),
            1,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 3)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    2
                )
            )
        )
    }

    @Test
    fun pawn_black_all_moves_valid_first_move() {
        val validMoves = Pawn(
            Coordinate(1, 1),
            1,
            null,
            false
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 4)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    3
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    2
                )
            )
        )
    }

    @Test
    fun pawn_black_all_moves_valid_first_move_forward_obstructed() {
        // note that this test obstructs with a different color piece
        // we should not be able to capture forward
        val validMoves = Pawn(
            Coordinate(1, 1),
            1,
            null,
            false
        ).getValidMoves(
            FakeBoard(
                false,
                Pawn(Coordinate(1, 2), 0, null, true)
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    2
                )
            )
        )
    }


    @Test
    fun pawn_black_left_move_invalid() {
        val validMoves = Pawn(
            Coordinate(0, 1),
            1,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    2
                )
            )
        )
    }

    @Test
    fun pawn_black_right_move_invalid() {
        val validMoves = Pawn(
            Coordinate(7, 1),
            1,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    7,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    2
                )
            )
        )
    }

    @Test
    fun pawn_black_cannot_move_into_black_piece() {
        val validMoves = Pawn(
            Coordinate(4, 1),
            1,
            null,
            true
        ).getValidMoves(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 2),
                    1,
                    null,
                    true
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(
            destinations.contains(
                Coordinate(
                    3,
                    2
                )
            )
        )
        assert(
            !destinations.contains(
                Coordinate(
                    4,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    5,
                    2
                )
            )
        )
    }
}
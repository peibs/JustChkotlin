package com.example.justchess.engine.piece

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.mocks.FakeBoard
import org.junit.Assert.fail
import org.junit.Test

class KnightUnitTest {
    @Test
    fun can_create_knight() {
        try {
            Knight(
                Coordinate(0, 1),
                0,
                null,
                true
            )
        } catch (e: Throwable) {
            fail("Failed to instantiate knight")
        }
    }

    @Test
    fun all_moves_valid() {
        val origin = Coordinate(3, 3)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 8)
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    0
                )
            )
        )
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
                    0,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    6
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
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    4
                )
            )
        )
    }

    @Test
    fun top_moves_not_valid() {
        val origin = Coordinate(3, 2)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    3
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
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    3
                )
            )
        )
    }

    @Test
    fun bottom_moves_not_valid() {
        val origin = Coordinate(3, 5)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    6
                )
            )
        )
    }

    @Test
    fun left_moves_not_valid() {
        val origin = Coordinate(2, 3)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
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
                    3,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    3,
                    6
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
        assert(
            destinations.contains(
                Coordinate(
                    5,
                    4
                )
            )
        )
    }

    @Test
    fun right_moves_not_valid() {
        val origin = Coordinate(5, 3)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
        assert(
            destinations.contains(
                Coordinate(
                    4,
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
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    6
                )
            )
        )
    }

    @Test
    fun no_moves_if_causes_king_to_be_checked() {
        val origin = Coordinate(3, 3)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(true))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }
}
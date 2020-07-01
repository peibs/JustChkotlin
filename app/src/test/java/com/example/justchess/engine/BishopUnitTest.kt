package com.example.justchess.engine

import com.example.justchess.engine.mocks.FakeBoard
import com.example.justchess.engine.piece.Bishop
import org.junit.Assert
import org.junit.Test

class BishopUnitTest {
    @Test
    fun can_create_bishop() {
        try {
            Bishop(
                Coordinate(1, 1),
                0,
                null
            )
        } catch (e: Throwable) {
            Assert.fail("Failed to instantiate bishop")
        }
    }

    @Test
    fun all_directions_valid_no_obstructions() {
        val bishop = Bishop(
            Coordinate(3, 3),
            0,
            null
        )
        val destinations = bishop.getValidDestinations(FakeBoard(false))
        assert(destinations.size == 13)
        // top left and bottom right
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(destinations.contains(Coordinate(z, z)))
        }
        // top right
        assert(destinations.contains(Coordinate(4, 2)))
        assert(destinations.contains(Coordinate(5, 1)))
        assert(destinations.contains(Coordinate(6, 0)))
        // bottom left
        assert(destinations.contains(Coordinate(2, 4)))
        assert(destinations.contains(Coordinate(1, 5)))
        assert(destinations.contains(Coordinate(0, 6)))
    }

    @Test
    fun top_right_obstructed_same_player() {

        val bishop = Bishop(
            Coordinate(3, 3),
            0,
            null
        )
        val destinations = bishop.getValidDestinations(
            FakeBoard(
                false,
                Bishop(Coordinate(4, 2), 0, null)
            )
        )
        assert(destinations.size == 10)
        // top left and bottom right
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(destinations.contains(Coordinate(z, z)))
        }
        // bottom left
        assert(destinations.contains(Coordinate(2, 4)))
        assert(destinations.contains(Coordinate(1, 5)))
        assert(destinations.contains(Coordinate(0, 6)))
    }

    @Test
    fun top_right_obstructed_different_player() {
        val bishop = Bishop(
            Coordinate(3, 3),
            0,
            null
        )
        val destinations = bishop.getValidDestinations(
            FakeBoard(
                false,
                Bishop(Coordinate(4, 2), 1, null)
            )
        )
        assert(destinations.size == 11)
        // top left and bottom right
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(destinations.contains(Coordinate(z, z)))
        }
        // top right can capture blocking piece
        assert(destinations.contains(Coordinate(4, 2)))
        // bottom left
        assert(destinations.contains(Coordinate(2, 4)))
        assert(destinations.contains(Coordinate(1, 5)))
        assert(destinations.contains(Coordinate(0, 6)))
    }
}
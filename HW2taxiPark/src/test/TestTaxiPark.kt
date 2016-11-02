package tests

import org.junit.Assert
import org.junit.Test
import taxiPark.*
import taxiPark.task.*

private val drivers = (1..8).map { Driver("Driver$it")}
private val passengers = (1..8).map { Passenger("Passenger$it")}

private fun driver(i: Int) = drivers.getOrNull(i - 1) ?: throw IllegalArgumentException("Invalid driver index: $i")
private fun passenger(i: Int) = passengers.getOrNull(i - 1) ?: throw IllegalArgumentException("Invalid passenger index: $i")

private fun drivers(range: IntRange) = range.map(::driver)
private fun drivers(vararg indices: Int) = indices.map(::driver)
private fun passengers(range: IntRange) = range.map(::passenger)
private fun passengers(vararg indices: Int) = indices.map(::passenger)

private fun taxiPark(driverIndexes: IntRange, passengerIndexes: IntRange, vararg orders: Order) =
        TaxiPark(drivers(driverIndexes), passengers(passengerIndexes), orders.toList())

private fun order(driverIndex: Int, vararg passengerIndexes: Int, duration: Int = 10, distance: Double = 3.0, discount: Double? = null) =
        Order(driver(driverIndex), passengers(*passengerIndexes), duration, distance, discount)

class TextTaxiPark {
    @Test
    fun testFakeDrivers() {
        val tp = taxiPark(1..3, 1..2, order(1, 1), order(1, 2))
        Assert.assertEquals(drivers(2, 3).toSet(), tp.findFakeDrivers().toSet())
    }

    @Test
    fun testFaithfulPassengers() {
        val tp = taxiPark(1..3, 1..5, order(1, 1), order(2, 1), order(1, 4), order(3, 4), order(1, 5), order(2, 5), order(2, 2))
        Assert.assertEquals(passengers(1, 4, 5), tp.findFaithfulPassengers(1))
    }

    @Test
    fun testFrequentPassengers() {
        val tp = taxiPark(1..2, 1..4, order(1, 1), order(1, 1), order(1, 1, 3), order(1, 3), order(1, 2), order(2, 2))
        Assert.assertEquals(passengers(1, 3), tp.findFrequentPassengers(driver(1)))
    }

    @Test
    fun testSmartPassengers() {
        val tp = taxiPark(1..2, 1..2, order(1, 1, discount = 0.1), order(2, 2))
        Assert.assertEquals(passengers(1), tp.findSmartPassengers())
    }

    @Test
    fun testTheMostFrequentTripDuration() {
        val tp = taxiPark(1..3, 1..5, order(1, 1, duration = 10), order(3, 4, duration = 30),
                order(1, 2, duration = 20), order(2, 3, duration = 30))
        Assert.assertEquals(30..39, tp.findTheMostFrequentTripDuration())
        Assert.assertEquals(null, taxiPark(1..1, 1..1).findTheMostFrequentTripDuration())
    }

    @Test
    fun testParetoPrincipleSuceeds() {
        val tp = taxiPark(1..8, 1..8, order(1, 1, duration = 200, distance = 200.0),
                order(2, 2), order(3, 3), order(4, 4), order(5, 5), order(6, 6), order(7, 7))
        Assert.assertTrue(tp.checkParetoPrinciple())
    }

    @Test
    fun testParetoPrincipleFails() {
        val tp = taxiPark(1..4, 1..4, order(1, 1, duration = 100, distance = 100.0),
                order(2, 2), order(3, 3), order(4, 4))
        Assert.assertFalse(tp.checkParetoPrinciple())
    }
}
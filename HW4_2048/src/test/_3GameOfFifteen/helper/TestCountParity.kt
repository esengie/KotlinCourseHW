package tests

import org.junit.Assert
import org.junit.Test
import task.countParity

class TestCountParity {
    fun testPermutation(permutation: List<Int>, parity: Boolean) {
        Assert.assertEquals("This permutation is ${if (parity) "even" else "odd"}: $permutation", parity, countParity(permutation))
    }

    fun testPermutation(shortPermutation: Int, parity: Boolean) {
        val permutation = shortPermutation.toString().map { "$it".toInt() }
        testPermutation(permutation, parity)
    }

    fun testEven(shortPermutation: Int) = testPermutation(shortPermutation, true)
    fun testOdd(shortPermutation: Int) = testPermutation(shortPermutation, false)

    @Test fun testEven0() = testEven(123)
    @Test fun testEven1() = testOdd(2134)
    @Test fun testEven2() = testOdd(1324)
    @Test fun testEven3() = testOdd(3214)
    @Test fun testEven4() = testOdd(1243)
    @Test fun testEven5() = testOdd(4123)
    @Test fun testEven6() = testOdd(2413)
    @Test fun testEven7() = testOdd(3142)
    @Test fun testEven8() = testOdd(1432)
    @Test fun testEven9() = testOdd(4312)
    @Test fun testEven10() = testOdd(2341)
    @Test fun testEven11() = testOdd(4231)
    @Test fun testEven12() = testOdd(3421)

    @Test fun testOdd0() = testOdd(132)
    @Test fun testOdd1() = testEven(1234)
    @Test fun testOdd2() = testEven(3124)
    @Test fun testOdd3() = testEven(2314)
    @Test fun testOdd4() = testEven(2143)
    @Test fun testOdd5() = testEven(1423)
    @Test fun testOdd6() = testEven(4213)
    @Test fun testOdd7() = testEven(1342)
    @Test fun testOdd8() = testEven(4132)
    @Test fun testOdd9() = testEven(3412)
    @Test fun testOdd10() = testEven(3241)
    @Test fun testOdd11() = testEven(2431)
    @Test fun testOdd12() = testEven(4321)

    @Test fun testStart() = testPermutation((1..15).toList(), true)
}
package tests

import org.junit.Assert
import org.junit.Test
import taxiPark.task.isNice

class TestNiceStrings {
    @Test
    fun testVowelLetters() {
        // всего две гласных буквы, нужно хотя бы три
        Assert.assertFalse("abcdefghhk".isNice())
    }

    @Test
    fun testDuplicatedLetters() {
        // нет сдвоенной буквы
        Assert.assertFalse("abcdefghik".isNice())
        Assert.assertFalse("".isNice())
        Assert.assertFalse("a".isNice())
    }

    @Test
    fun testBadWord() {
        // есть подстрока ba
        Assert.assertFalse("bacdefgik".isNice())
    }

    @Test
    fun testNice() {
        // все три условия выполнены:
        // 1. нет подстрок bu, ba, be
        // 2. есть три гласные aei
        // 3. есть сдвоенная буква ff
        Assert.assertTrue("abcdeffghik".isNice())
    }
}
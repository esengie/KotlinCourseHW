package task

/*
This function should return the parity of the permutation.
true - the permutation is even
false - the permutation is odd
https://en.wikipedia.org/wiki/Parity_of_a_permutation

If the game of fifteen is started with the wrong parity, you can't get the correct result
  (numbers sorted in the right order, empty cell at last).
Thus the initial permutation should be correct.
 */
fun countParity(permutation: List<Int>): Boolean {
    val arr = permutation.toMutableList()
    var cnt = 0
    for (j in 1..arr.size - 1){
        var i = j - 1
        val processedValue = arr[j]
        while ( (i >= 0) && (arr[i] > processedValue) ){
            arr[i + 1] = arr[i]
            cnt++
            i--
        }
        arr[i + 1] = processedValue
    }

    return cnt % 2 == 0
}
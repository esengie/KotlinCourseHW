package task

/*
If the function double("a") returns "aa",
then the function moveAndMergeEqual transforms the input in the following way:
  a, a, b -> aa, b
  b, null, a, a -> b, aa
  a, a, null, a -> aa, a
  a, null, a, a -> aa, a
Examples and tests in TestMoveAndMergeValues.kt
*/

fun <T : Any> List<T?>.moveAndMergeEqual(double: (T) -> T): List<T> {
    var res: MutableList<T> = this.filterNotNull().toMutableList()
    var cnt = 1
    while(cnt < res.size){
        if (res[cnt-1] == res[cnt]) {
            res[cnt - 1] = double(res[cnt - 1])
            res.removeAt(cnt)
        }
        ++cnt
    }
    return res
}

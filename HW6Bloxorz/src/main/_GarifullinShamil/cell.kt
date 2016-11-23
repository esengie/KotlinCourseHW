package _GarifullinShamil

data class Cell(val i: Int, val j: Int, val c: Char){
    val type : CellType
    init {
        when (c) {
            '*' -> type = CellType.CELL
            '.' -> type = CellType.LIGHT
            'S' -> type = CellType.CELL
            'T' -> type = CellType.CELL
            else -> {
                if (c.isUpperCase()){
                    type = CellType.SWITCH
                } else {
                    type = CellType.BRIDGE
                }
            }}
    }
}

enum class CellType {
    CELL, LIGHT, SWITCH, BRIDGE
}

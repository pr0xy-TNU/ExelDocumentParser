fun main(args: Array<String>) {
    var counter : Int  = 10


}

class UserPosition(var lat: Double = 23.2, var lng: Double = 34.12) {

    init {
        lat = 23.23
        lng = 22.12
    }

    override fun toString(): String {
        return "UserPosition{$lat, $lng}"
    }
}

fun getUserName() = "Name"


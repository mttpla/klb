package klb

interface ProviderIF {
    var id: String
    fun get(): String
    fun check(): Boolean
}
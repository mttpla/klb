package klb

import ProviderIF
import java.util.UUID

class Provider() : ProviderIF {
    override var id = UUID.randomUUID().toString()

    override fun get(): String 
    {
        return id;
    }

    override fun check(): Boolean{
        return true;
    }
}

package klb

import klb.ProviderIF

interface LoadBalancerIF {
    fun get(): String
    fun include(provider: ProviderIF): String
    fun exclude(provider: ProviderIF): String
    fun register(providerList: List<ProviderIF>): String
}
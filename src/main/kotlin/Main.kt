package klb

import klb.LoadBalancerFactory;

fun main() {
    println("KLB")
    var loadBalancer = LoadBalancerFactory().getLoadBalancer("sequenceLoadBalancer",15);
    var provider = Provider();
    loadBalancer.register(listOf(provider));
    var provider2 = Provider();
    loadBalancer.include(provider2);
    var result = loadBalancer.get();
    loadBalancer.exclude(provider2);

    
    println("First call: "+ result)

}
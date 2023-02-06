package klb

import klb.SequenceLoadBalancer

class LoadBalancerFactory(){

    fun getLoadBalancer(loadBalancerType: String, heartBeatCheckerSeconds: Int): LoadBalancer {
    when (loadBalancerType) {
    "sequenceLoadBalancer" -> return SequenceLoadBalancer(heartBeatCheckerSeconds)
    else -> {
        // Default 
        return RandomLoadBalancer(heartBeatCheckerSeconds)
    }
}


    }
}
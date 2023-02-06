package klb

import klb.ProviderIF


class RandomLoadBalancer(heartBeatCheckerSeconds: Int): LoadBalancer(heartBeatCheckerSeconds: Int) {
    var currentIndex: Int = 0;

    override fun getNextProvider() : ProviderIF{
        return activeProviderList.get((0..activeProviderList.size).random());
    }

}
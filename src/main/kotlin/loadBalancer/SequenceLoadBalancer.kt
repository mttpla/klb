package klb

import klb.ProviderIF

class SequenceLoadBalancer(heartBeatCheckerSeconds: Int): LoadBalancer(heartBeatCheckerSeconds: Int) {
    var currentIndex: Int = 0;

    override fun getNextProvider() : ProviderIF{
        try{
            currentIndex++;
            return activeProviderList.get(currentIndex);
        }catch (e: java.lang.IndexOutOfBoundsException) 
        {
            currentIndex = 0;
            return activeProviderList.get(0);
        }
    }

}
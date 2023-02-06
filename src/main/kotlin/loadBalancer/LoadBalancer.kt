package klb

import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.atomic.AtomicInteger
import java.nio.channels.Channel
import klb.ProviderIF


abstract class LoadBalancer(heartBeatCheckerSeconds: Int): LoadBalancerIF {
    
    var activeProviderList = mutableListOf<ProviderIF>();
    // providers that can be back active after next check
    var onProviderList = mutableListOf<ProviderIF>();
    // providers that fails the check
    var offProviderList = mutableListOf<ProviderIF>();
    var heartBeatCheckerPeriod: Long = (heartBeatCheckerSeconds * 1000).toLong();
    var runningRequestNumber = AtomicInteger();

    val timer = Timer();
    val timerTask: TimerTask = object : TimerTask() {
        override fun run() {
            heartBeatChecker();
        }
    }

    init {
        // start the Heart beat checker
        timer.scheduleAtFixedRate(timerTask, heartBeatCheckerPeriod, heartBeatCheckerPeriod);
    }
    

    abstract fun getNextProvider(): ProviderIF;

    fun sendToProvider(): String{
        while(activeProviderList.size>0){
            var provider = getNextProvider();
            try{
                return provider.get();
            }catch (e: Exception){
                // remove the provider 
                activeProviderList.remove(provider);
                offProviderList.add(provider);
            } 
        }   
        // no provider get response
        return NO_PROVIDER_AVAILABLE;
    }

    override fun get(): String {
        runningRequestNumber.incrementAndGet();
        if(runningRequestNumber.toInt() > MAX_PARALLEL_REQUEST_PER_PROVIDER * activeProviderList.size){
            return MAX_CLUSTER_CAPACITY_LIMIT;
        }
        var result = sendToProvider();
        runningRequestNumber.decrementAndGet();
        return result;
    }
    

    override fun register(providerList: List<ProviderIF>): String {
        if(activeProviderList.size + providerList.size > MAX_PROVIDER_LIST_SIZE){
            return REGISTERED_FAILED_PROVIDER_NUMBER;
        }
        providerList.forEach {
            // adding, we do not check if present or not, the size allow to add all providers
            include(it);
        }
        return PROVIDERS_REGISTERED;
    }

    override fun include(provider: ProviderIF): String{
        if(activeProviderList.size > MAX_PROVIDER_LIST_SIZE){
            return MAX_PROVIDER_NUMBER;
        }
        if(activeProviderList.contains(provider)){
            return PROVIDER_ALREADY_PRESENT;
        }
        activeProviderList.add(provider)
        return PROVIDER_ADDED;
    }

    override fun exclude(provider: ProviderIF): String{
        if(activeProviderList.remove(provider)){
            return PROVIDER_REMOVED;
        }
        return PROVIDER_NOT_PRESENT;
    }

    fun heartBeatChecker(){
        //check the active provider
        activeProviderList.forEach {
            try{
                if(!it.check()){
                //remove from actice list
                exclude(it);
                offProviderList.add(it);
            }
            }catch(e Exception){
                exclude(it);
                offProviderList.add(it);
                // TODO: LOG exception
            }
         }
        //if activeProviderList is equal to max no needed more checks.
        // No older provider will be added 
        if(activeProviderList.size == MAX_PROVIDER_LIST_SIZE){
            return;
        }

        // keep simple, do not add states. 
        // The specs tells about only 3 states (active, on, off)
        onProviderList.forEach {
            try{
                if(!it.check()){
                    //remove from on list
                    onProviderList.remove(it);
                    offProviderList.add(it);
                }else{
                    //set as active provider
                    onProviderList.remove(it);
                    activeProviderList.add(it);
                }
            }catch (e Exception){
                onProviderList.remove(it);
                offProviderList.add(it);
                // TODO: LOG exception
            }
            
         }
        onProviderList.forEach { 
            try{
                if(it.check()){
                //remove from on list
                offProviderList.remove(it);
                onProviderList.add(it);
                }
            }catch (e Exception){
                //keep in offProviderList
                // TODO: LOG exception
            }
         }

    }
}
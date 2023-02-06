import kotlin.test.Test
import kotlin.test.assertTrue

internal class LoadBalanecerTest {

    //TODO: list of tests

    //@Test
    // using register add more then 10 provider
    // -> got error message
    // -> the activeProviderList is unchanged
    
    //@Test
    // setup the loadBalancer with 5 provider and register 6 news provider
    // -> got error message
    // -> the activeProviderList is unchanged

    //@Test
    // add 11 provider using include: check 10 ok and fail message at the end
    // -> got 10 ook message
    // -> the activeProviderList is changed without the final provider

    //@Test
    // exclude a provider in the activeList
    // -> got OK
    
    //@Test
    // exclude a provider not in the activeList
    // -> got not present provider

    //@Test
    // write a mock to slow down the provider answer. Test to hit the capacity limit and got the error

    //@Test
    // test the heart beat checker
    // remove a provider 
    // -> check that it is removed from activeProviderList
    // reactive a provider
    // -> check that it is move in onProviderList and after again in activeProviderList
    
    //@Test
    // populate the lists and verify that if activeProviderList's size is 10 nothing happen

    //@Test
    // have a list of failure provider and ask get
    // -> the offProviderList is populated
    // -> got the error message
    

    
}
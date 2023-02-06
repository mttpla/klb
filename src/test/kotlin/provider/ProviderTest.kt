import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail
import klb.Provider


internal class ProviderTest {

    private val provider: Provider = Provider()

    @Test
    fun checkTest() {
        assertTrue(provider.check());
    }

    @Test
    fun getDifferentName() {
        var PROVIDER_NUMBER = 100
        var nameList = mutableListOf<String>();
        for (i in 1..PROVIDER_NUMBER) {
            var name = Provider().get();
            if(nameList.contains(name)){
                fail("Provider name present")
            }else{
                nameList.add(name);
            }
        } 
    }
}
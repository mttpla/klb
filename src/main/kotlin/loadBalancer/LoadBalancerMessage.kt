package klb

// improvement: this string message can be a object like:

// class Message {
//     var status : String; // OK or failed
//     var code: Int; // status code, like http code: 200 ok, 500 internal server error
//     var message: String;

//     fun toJson() {} ; // usefull function to send out the message
// }

// error message
const val QUEUE_IS_FULL: String = "max incoming request exided";
const val MAX_PROVIDER_NUMBER: String = "registered failed";
const val PROVIDER_ALREADY_PRESENT: String = "provider already in the list";
const val REGISTERED_FAILED_PROVIDER_NUMBER: String = "registered failed. Max number of provider achivied";
const val NO_PROVIDER_AVAILABLE = "no available provider at moment";
const val MAX_CLUSTER_CAPACITY_LIMIT = "the system is busy, to many concurrent requests"

// ok message
const val PROVIDER_ADDED: String = "provider added";
const val PROVIDERS_REGISTERED: String = "provider registered";
const val PROVIDER_REMOVED: String = "provider removed";
const val PROVIDER_NOT_PRESENT: String = "provider not found";

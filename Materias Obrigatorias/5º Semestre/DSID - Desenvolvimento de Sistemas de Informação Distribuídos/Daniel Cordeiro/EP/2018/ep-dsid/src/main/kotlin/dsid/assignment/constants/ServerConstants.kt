package dsid.assignment.constants

lateinit var serverAddress: String
var serverPort: Int = -1
lateinit var technologyToUse: Technology

enum class Technology {
    GRPC, OTHER
}
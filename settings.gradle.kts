rootProject.name = "lab1-Java-IS-ITMO"
include("Bank")
include("BankConsole")
include("lab2")
include("lab2:dao")
findProject(":lab2:dao")?.name = "dao"
include("lab2:service")
findProject(":lab2:service")?.name = "service"
include("lab2:controller")
findProject(":lab2:controller")?.name = "controller"
include("lab2:controller")


include("lab3")
include("lab3:dao")
findProject(":lab3:dao")?.name = "dao"
include("lab3:service")
findProject(":lab3:service")?.name = "service"
include("lab3:controller")
findProject(":lab3:controller")?.name = "controller"


include("lab4")
include("lab4:dao")
findProject(":lab4:dao")?.name = "dao"
include("lab4:service")
findProject(":lab4:service")?.name = "service"
include("lab4:controller")
findProject(":lab4:controller")?.name = "controller"
include("lab4:security")
findProject(":lab4:security")?.name = "security"


include("lab5")
include("lab5:base-domain")
findProject(":lab5:base-domain")?.name = "base-domain"

include("lab5:cat")
findProject(":lab5:cat")?.name = "cat"
include("lab5:owner")
findProject(":lab5:owner")?.name = "owner"
include("lab5:user")
findProject(":lab5:user")?.name = "user"
include("lab5:user")
findProject(":lab5:user")?.name = "user"
include("lab5:owner")
findProject(":lab5:owner")?.name = "owner"
include("lab5:cat-client")
findProject(":lab5:cat-client")?.name = "cat-client"
include("lab5:owner-client")
findProject(":lab5:owner-client")?.name = "owner-client"
include("lab5:user-client")
findProject(":lab5:user-client")?.name = "user-client"
include("lab5:api-gateway")
findProject(":lab5:api-gateway")?.name = "api-gateway"
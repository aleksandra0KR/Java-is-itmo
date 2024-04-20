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


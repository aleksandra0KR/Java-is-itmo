rootProject.name = "lab1-Java-IS-ITMO"
include("Bank")
include("BankConsole")
include("lab2")
include("lab2:DAO")
findProject(":lab2:DAO")?.name = "DAO"
include("lab2:service")
findProject(":lab2:service")?.name = "service"
include("lab2:controller")
findProject(":lab2:controller")?.name = "controller"
include("lab2:controller")
findProject(":lab2:controller")?.name = "controller"

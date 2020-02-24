package machine

import java.util.*

val scanner = Scanner(System.`in`)

fun main() {

    val coffeMachine = CoffeMachine()

    while (true) {
        println("Write action (buy, fill, take, remaining, exit): ")

        coffeMachine.doAction(scanner.next())
    }
}

class CoffeMachine() {

    var state: String = ""
    var water: Int = 400
    var milk: Int = 540
    var beans: Int = 120
    var cups: Int = 9
    var money: Int = 550

    fun doAction(action: String) {
        when (action) {
            "buy" -> buy()
            "fill" -> fill()
            "take" -> take()
            "remaining" -> remaining()
            "exit" -> exit()
        }
    }

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        when (scanner.next()) {
            "1" -> makeDrink("espresso")
            "2" -> makeDrink("latte")
            "3" -> makeDrink("cappuccino")
        }
    }

    fun fill() {
        println("Write how many ml of water do you want to add: ")
        water += scanner.nextInt()
        println("Write how many ml of milk do you want to add: ")
        milk += scanner.nextInt()
        println("Write how many grams of coffee beans do you want to add: ")
        beans += scanner.nextInt()
        println("Write how many disposable cups of coffee do you want to add: ")
        cups += scanner.nextInt()
    }

    fun take() {
        println("I gave you $$money")
        money = 0
    }

    fun makeDrink(drinkType: String) {

        when (drinkType) {
            "espresso" -> {
                if (enoughRessources(drinkType)) {
                    water -= 250
                    milk -= 0
                    beans -= 16
                    money += 4
                    cups--
                }
            }
            "latte" -> {
                if (enoughRessources(drinkType)) {
                    water -= 350
                    milk -= 75
                    beans -= 20
                    money += 7
                    cups--
                }
            }
            "cappuccino" -> {
                if (enoughRessources(drinkType)) {
                    water -= 200
                    milk -= 100
                    beans -= 12
                    money += 6
                    cups--
                }
            }
        }
    }

    fun remaining() {
        println("The coffee machine has: ")
        println("$water of water")
        println("$milk of milk")
        println("$beans of coffee beans")
        println("$cups of disposable cups")
        println("$$money of money")
    }

    fun exit() {
        System.exit(0)
    }

    fun enoughRessources(drinkType: String): Boolean {

        var neededWater = 0
        var neededMilk = 0
        var neededBeans = 0

        when (drinkType) {
            "espresso" -> {
                neededWater = 250
                neededBeans = 16
            }
            "latte" -> {
                neededWater = 350
                neededMilk = 75
                neededBeans = 20
            }
            "cappuccino" -> {
                neededWater = 200
                neededMilk = 100
                neededBeans = 12
            }
        }

        return if (neededWater > water) {
            println("Sorry, not enough water!")
            false
        } else if (neededMilk > milk) {
            println("Sorry, not enough milk!")
            false
        } else if (neededBeans > beans) {
            println("Sorry, not enough beans!")
            false
        } else if (cups < 1) {
            println("Sorry, not enough cups!")
            false
        } else {
            println("I have enough resources, making you a coffee!")
            true
        }
    }
}


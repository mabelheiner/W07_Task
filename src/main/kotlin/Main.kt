package org.example
import java.util.Scanner
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

data class FoodListItem(
    val foodItem: FoodItem,
    val points: Int
)

data class FoodItem (
    val name: String,
    val calories: Int,
    val fat: Double,
    val carbs: Double,
    val protein: Double
)

class FoodListSystem {
    private val foodList = mutableListOf<FoodListItem>()

    fun addFood(foodListItem: FoodListItem) {
        foodList.add(foodListItem)
    }

    fun searchFood(points: Int) {
        foodList.filter { it.points.equals(points)}
    }

    fun deleteFood(points: Int){
        val food = foodList.find{it.points.equals(points)}
        food?.let { foodList.remove(it) }
    }

    fun displayAllFoods(){
        foodList.forEach { println(it) }
    }
}

object PointCalculator {
    fun calculatePoints(foodItem: FoodItem): Int {
        return ((foodItem.calories / 50) + (foodItem.fat * 12) - (foodItem.carbs * 5)).toInt()
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val foodSystem = FoodListSystem()

    val foodItems = listOf(
        FoodItem("Apple", 95, 0.3, 25.0, 0.5),
        FoodItem("Banana", 105, 0.4, 27.0, 1.3),
        FoodItem("Chicken Breast", 165, 3.6, 0.0, 31.0)
    )

    var choice: Int
    do {
        println("\nFood Management System")
        println("1. Calculate food")
        println("2. Add food to list")
        println("3. Search food by points")
        println("4. Delete food item")
        println("5. Display all food items")
        println("6. Exit")
        print("Enter your choice: ")
        choice = scanner.nextInt()

        when (choice) {
            1 -> {
                println("Enter the details of the food item:")
                val start = scanner.nextLine()
                print("Name: ")
                val name = scanner.nextLine()
                print("Calories: ")
                val calories = scanner.nextInt()
                print("Fat (in grams): ")
                val fat = scanner.nextDouble()
                print("Carbs (in grams: ")
                val carbs = scanner.nextDouble()
                print("Protein (in grams): ")
                val protein = scanner.nextDouble()

                val newFoodItem = FoodItem(name, calories, fat, carbs, protein)
                val points = PointCalculator.calculatePoints(newFoodItem)
                println("Points for $name: $points")
            }

            2 -> {
                println("Enter the details of the food item:")
                print("Name: ")
                val name = scanner.nextLine()
                print("Calories: ")
                val calories = scanner.nextInt()
                print("Fat (in grams): ")
                val fat = scanner.nextDouble()
                print("Carbs (in grams: ")
                val carbs = scanner.nextDouble()
                print("Protein (in grams): ")
                val protein = scanner.nextDouble()

                val newFoodItem = FoodItem(name, calories, fat, carbs, protein)
                val points = PointCalculator.calculatePoints(newFoodItem)
                val newFoodListItem = FoodListItem(newFoodItem, points)
                foodSystem.addFood(newFoodListItem)
            }
        }
    } while (choice != 5)
}
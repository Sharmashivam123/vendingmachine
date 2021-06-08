# Vending Machine
Problem Statement
You have to code a working coffee machine which can stock ingredients, prepare beverages, display the current quantity of stock available. One or more ingredients will be used to prepare beverages. Same ingredients can be used to prepare multiple beverages. Initial ingredients’ stock and the ingredients required to prepare each beverage will be given. Coffee machine displays success messages for stocking ingredients and preparing beverages. It also displays proper error messages with all the missing ingredients in case of unavailability of stock.

The coffee machine can stock with the following ingredients:

Motivation behind design decisions
         - Factory design pattern is used to encapsulate the creation logic of VendingMachine.
         - Adapter pattern is used to create Inventory by wrapping java.util.Map
         - java.lang.Enum is used to represent Item and Coins, because of the following benefits
                - compile-time safety against entering an invalid item and invalid coin.
                - no need to write code for checking if the selected item or entered coin is valid.
                - reusable and well encapsulated.
         - long is used to represent price and totalSales, which are the amount because we are dealing in cents.
           Not used BigDecimal to represent money, because the vending machine can only hold a limited amount, due to the finite capacity of coin inventory.

# 📱 BMI Calculator App

## Overview
This is a simple **BMI (Body Mass Index) Calculator** app built for Android using **Kotlin** and **Jetpack Compose**. The app allows users to calculate their BMI based on weight and height, and categorizes the result (Underweight, Normal, Overweight, Obesity).

## Features
- Calculate BMI based on user input (weight & height)
- Categorizes BMI result (Underweight, Normal, Overweight, Obesity)
- Real-time UI updates using Jetpack Compose
- Error handling for invalid inputs

## 🛠️ Technologies Used
- **Kotlin**: Primary programming language
- **Jetpack Compose**: For building the user interface
- **Android SDK**: Core Android libraries
- **MVVM**: Architecture pattern used to separate concerns and enhance testability
- **Clean Architecture**: Ensures modular and scalable code design

## Architecture
The project follows **MVVM (Model-View-ViewModel)** architecture, ensuring a separation of concerns between the UI and business logic. Additionally, it follows **Clean Architecture** principles to maintain modularity and scalability.

### **Key Components:**
- **UI Layer (View)**: Built with Jetpack Compose, providing reactive and declarative UI.
- **ViewModel**: Handles business logic, exposes data to the UI, and manages UI state.
- **Repository**: Abstracts data sources and provides data to the ViewModel.

## How to Build & Run
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/bmi-calculator-app.git

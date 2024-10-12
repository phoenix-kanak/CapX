# Overview

This is an Android application that fetches stocks data from the [Financial Modeling Prep API]([https://www.episodate.com/api](https://site.financialmodelingprep.com/developer/docs#symbol-list-stock-list)) and displays it in a user-friendly format. The app includes features like searching for stocks dynamically in the list.
- **ViewModel**: Acts as the mediator between the View and Model, holding and preparing data for the UI.
- **View**: The UI components, including RecyclerView for lists and Activities for screens.

### Libraries Used
- **Retrofit**: For networking and fetching data from the Financial Modeling Prep API.
- **RecyclerView**: For displaying lists of stocks.
- **LiveData & ViewModel**: To observe and manage UI-related data.

### Features
- Fetches a list of stocks from the Financial Modeling Prep API.
- Allows users to search for stocks.
  
### Setup Instructions
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/stocks-app.git
    ```
2. Open the project in **Android Studio**.
3. Run the app on an emulator or a physical device.

### How It Works
- The app fetches data from the Financial Modeling Prep API using Retrofit.
- The fetched data is observed using LiveData in ViewModels and displayed in RecyclerViews.

